package vn.HKT.fillters;

import java.io.IOException;
import java.util.Enumeration;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HKT.entities.Users;
import vn.HKT.utils.Constant;

@WebFilter(urlPatterns = { "/*" })
public class SecurityFilter implements Filter {

	// Danh sách các phần mở rộng tệp hợp lệ cần bỏ qua
	private static final String[] STATIC_EXTENSIONS = { ".png", ".jpg", ".jpeg", ".gif", ".ico", ".css", ".js",
			".pdf" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter initialized!");
	}

	@Override
	public void destroy() {
		System.out.println("SecurityFilter destroyed!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		String servletPath = req.getServletPath();
		String queryString = req.getQueryString();

		try {
			// Bỏ qua các tệp tĩnh
			if (isStaticResource(servletPath)) {
				chain.doFilter(request, response);
				return;
			}

			// Kiểm tra người dùng trong session
			Users user = (Users) session.getAttribute("account");

			// Nếu người dùng là admin hoặc user (hệ thống tin tưởng người dùng), cho phép
			// tiếp tục
			if (user != null
					&& ((user.getRole().getRoleName() == "admin") || (user.getRole().getRoleName() == "user"))) {
				chain.doFilter(request, response);
				return;
			}

			// Kiểm tra nội dung độc hại trong URL và query string
			if (isMalicious(servletPath) || (queryString != null && isMalicious(queryString))) {
				logAndBlock(resp, "Blocked request with potential attack", servletPath + "?" + queryString);
				return;
			}

			// Kiểm tra tham số đầu vào của request
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement();
				String value = request.getParameter(param);

				// Nếu phát hiện chuỗi độc hại trong tham số
				if (value != null && isMalicious(value)) {
					logAndBlock(resp, "Blocked parameter with potential attack", param + " = " + value);
					return;
				}
			}

			// Nếu không phát hiện vấn đề, tiếp tục chuỗi lọc
			chain.doFilter(request, response);

		} catch (Exception e) {
			// Xử lý ngoại lệ không mong muốn
			System.err.println("SecurityFilter encountered an error: " + e.getMessage());
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error occurred.");
		}
	}

	// Phương thức kiểm tra nội dung độc hại
	private boolean isMalicious(String input) {
		return Constant.SQL_INJECTION_PATTERN.matcher(input).find() || Constant.SSTI_PATTERN.matcher(input).find()
				|| Constant.XSS_PATTERN.matcher(input).find()
				|| (input != null && Constant.PATH_TRAVERSAL_PATTERN.matcher(input).find());
	}

	// Phương thức kiểm tra xem có phải tài nguyên tĩnh không
	private boolean isStaticResource(String path) {
		if (path != null) {
			for (String ext : STATIC_EXTENSIONS) {
				if (path.toLowerCase().endsWith(ext)) {
					return true;
				}
			}
		}
		return false;
	}

	// Phương thức ghi log và chặn yêu cầu
	private void logAndBlock(HttpServletResponse resp, String logMessage, String detail) throws IOException {
		System.out.println(logMessage + ": " + detail);
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request blocked due to potential malicious content.");
	}
}