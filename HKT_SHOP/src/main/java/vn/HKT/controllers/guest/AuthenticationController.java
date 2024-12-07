package vn.HKT.controllers.guest;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HKT.entities.Users;
import vn.HKT.services.IUserService;
import vn.HKT.services.impl.UserServiceImpl;
import vn.HKT.utils.Constant;

@WebServlet(urlPatterns = { "/authentication/", "/authentication/login", "/authentication/signup",
		"/authentication/forgotpassword", "/authentication/logout" })
public class AuthenticationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		System.out.println("rememberedEmail attribute: " + req.getAttribute("rememberedEmail"));

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					String email = cookie.getValue();
					req.setAttribute("rememberedEmail", email);
					req.setAttribute("rememberChecked", true); // Đánh dấu checkbox
					System.out.println("Cookie received: " + cookie.getName() + " = " + cookie.getValue());
				}
			}
		} else {
			System.out.println("No cookies found.");
		}

// Hiển thị trang đăng nhập hoặc đăng ký dựa vào URL
		if (url.contains("/authentication/") || url.contains("/login") || url.contains("/signup")
				|| url.contains("/forgotpassword")) {
			req.getRequestDispatcher("/views/guest/authentication.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("/login")) {

			Enumeration<String> paramNames = req.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				System.out.println(paramName + ": " + req.getParameter(paramName));
			}

			System.out.println("Action URL: " + req.getRequestURI());

			// Xử lý đăng nhập
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("remember");

			System.out.println("Value of 'remember': " + remember);

			boolean isRememberMe = "remember-me".equals(remember);
			System.out.print("Trạng thái của Remember me: " + isRememberMe + "\n");

			// Kiểm tra xem email và password có hợp lệ không
			if (email.isEmpty() || password.isEmpty()) {
				req.setAttribute("errorMessage", "Email or password cannot be blank");
				req.getRequestDispatcher("/views/guest/authentication.jsp").forward(req, resp);
				return;
			}

			Users user = userService.login(email, password);
			if (user != null) {
				HttpSession session = req.getSession(true);
				session.setAttribute("account", user);

				if (isRememberMe) {
					Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, email);
					cookie.setMaxAge(30 * 24 * 60 * 60); // Cookie tồn tại 30 ngày
					cookie.setPath("/"); // Áp dụng trên toàn bộ ứng dụng
					resp.addCookie(cookie);
					System.out.println("Cookie created and sent: " + cookie.getName() + " = " + cookie.getValue());
				}

				resp.sendRedirect(req.getContextPath() + "/waiting");
			} else {
				req.setAttribute("errorMessage", "Email or password is incorrect");
				req.getRequestDispatcher("/views/guest/authentication.jsp").forward(req, resp);
			}

		} else if (url.contains("/signup")) {
			// Xử lý đăng ký
			String email = req.getParameter("email");
			String fullName = req.getParameter("fullname");
			String password = req.getParameter("password");
			if (email.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
				req.setAttribute("errorMessage", "Email, account and password cannot be blank");
				req.getRequestDispatcher("/views/authentication.jsp").forward(req, resp);
				return;
			}
			// Kiểm tra xem đăng ký có thành công không
			boolean isRegistered = userService.register(email, fullName, password);

			if (isRegistered) {
				// Đăng ký thành công, chuyển hướng đến trang đăng nhập
				resp.sendRedirect(req.getContextPath() + "/authentication/login");
			} else {
				// Đăng ký không thành công, hiển thị thông báo lỗi
				req.setAttribute("errorMessage", "Email already exists or an error has occurred.");
				req.getRequestDispatcher("/views/authentication.jsp").forward(req, resp);
			}
		} else if (url.contains("/forgotpassword")) {
			String email = req.getParameter("email");

			if (email.isEmpty()) {
				req.setAttribute("errorMessage", "Please enter email.");
				req.getRequestDispatcher("/views/guest/authentication.jsp").forward(req, resp);
				return;
			}

			// Tìm người dùng theo email
			Users user = userService.FindByEmail(email);

			if (user != null) {
				// Gọi service để tạo token và gửi email
				try {
					userService.sendResetToken(email);
					req.setAttribute("successMessage", "Password reset email has been sent.");
				} catch (Exception e) {
					req.setAttribute("errorMessage", "An error occurred while sending the email. Please try again.");
				}
			} else {
				req.setAttribute("errorMessage", "No users found with this email.");
			}

			// Quay về trang forgot password với thông báo
			req.getRequestDispatcher("/views/guest/authentication.jsp").forward(req, resp);
		}

		else if (url.contains("/logout")) {

			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}

}