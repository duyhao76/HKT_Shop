package vn.HKT.controllers.guest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import vn.HKT.services.IUserService;
import vn.HKT.services.Impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/authentication/reset-password", "/authentication/create-password" })
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String token = req.getParameter("token");

		// Chuyển hướng đến trang reset-password.jsp và đặt các tham số
		if ("reset".equals(action)) {
			req.setAttribute("token", token);
			req.setAttribute("action", "reset"); // Đặt action để phân biệt mục đích
		} else if ("create".equals(action)) {
			req.setAttribute("action", "create"); // Đặt action là tạo mật khẩu cho bên thứ ba
		}
		req.getRequestDispatcher("/views/guest/reset-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String newPassword = req.getParameter("password");

		if (newPassword == null || newPassword.length() < 6) {
			req.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 6 ký tự.");
			req.getRequestDispatcher("/views/guest/reset-password.jsp").forward(req, resp);
			return;
		}

		try {
			if ("reset".equals(action)) {
				String token = req.getParameter("token");
				System.out.println("Token trước khi gọi updatePassword: " + token);

				// Kiểm tra token và cập nhật mật khẩu
				userService.updatePasswordByToken(newPassword, token);
				req.getSession().setAttribute("successMessage", "Mật khẩu đã được cập nhật thành công.");
				resp.sendRedirect(req.getContextPath() + "/authentication/login");

			} else if ("create".equals(action)) {
				// Lấy email từ session cho người dùng đăng nhập bên thứ ba lần đầu
				String email = (String) req.getSession().getAttribute("email");
				if (email != null) {
					userService.updatePasswordByEmail(email, newPassword); // Cập nhật mật khẩu theo email
					req.getSession().setAttribute("successMessage", "Tài khoản của bạn đã được hoàn tất.");
					resp.sendRedirect(req.getContextPath() + "/user/home.jsp");
				} else {
					req.setAttribute("errorMessage", "Không tìm thấy thông tin người dùng.");
					req.getRequestDispatcher("/views/guest/reset-password.jsp").forward(req, resp);
				}
			}
		} catch (IllegalArgumentException e) {
			req.setAttribute("errorMessage", "Token không hợp lệ hoặc đã hết hạn.");
			req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("errorMessage", "Lỗi không xác định: " + e.getMessage());
			req.getRequestDispatcher("/views/guest/reset-password.jsp").forward(req, resp);
			e.printStackTrace(); // Log lỗi chi tiết ra console để dễ debug hơn
		}
	}
}
