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
import vn.HKT.utils.Constant;

@WebFilter(urlPatterns = { "/*" })
public class SecurityFilter implements Filter {

	// Danh sách các phần mở rộng tệp hợp lệ cần bỏ qua
	private static final String[] STATIC_EXTENSIONS = { ".png", ".jpg", ".jpeg", ".gif", ".ico", ".css", ".js" };

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

		String servletPath = req.getServletPath();
		String queryString = req.getQueryString();

		// Bỏ qua các tệp tĩnh (static resources)
		if (isStaticResource(servletPath)) {
			chain.doFilter(request, response);
			return;
		}

		// Kiểm tra nội dung độc hại trong URL hoặc query string
		if (isMalicious(servletPath) || (queryString != null && isMalicious(queryString))) {
			System.out.println("Blocked request with potential attack: " + servletPath + "?" + queryString);

			// Trả về lỗi 400 Bad Request nếu phát hiện nội dung độc hại
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request contains malicious content!");
			return;
		}

		// Kiểm tra tham số đầu vào của request
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String param = paramNames.nextElement();
			String value = request.getParameter(param);

			// Nếu phát hiện chuỗi độc hại trong tham số
			if (isMalicious(value)) {
				System.out.println("Blocked parameter with potential attack: " + param + " = " + value);
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Detected malicious input!");
				return;
			}
		}

		// Tiếp tục chuỗi lọc nếu không phát hiện vấn đề
		chain.doFilter(request, response);
	}

	// Phương thức kiểm tra nội dung độc hại
	private boolean isMalicious(String input) {
		return Constant.SQL_INJECTION_PATTERN.matcher(input).find() || Constant.SSTI_PATTERN.matcher(input).find()
				|| Constant.XSS_PATTERN.matcher(input).find() || Constant.PATH_TRAVERSAL_PATTERN.matcher(input).find();
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

}
