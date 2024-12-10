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

	    try {
	        // Kiểm tra các điều kiện và gọi chain.doFilter nếu hợp lệ
	        if (isStaticResource(req.getServletPath())) {
	            chain.doFilter(request, response);
	            return;
	        }
	        // Kiểm tra người dùng và các điều kiện khác
	        Users user = (Users) req.getSession().getAttribute("account");
	        if (user != null && (user.getRole().getRoleName().equals("admin") || user.getRole().getRoleName().equals("user"))) {
	            chain.doFilter(request, response);
	            return;
	        }

	        // Kiểm tra URL và tham số độc hại
	        String servletPath = req.getServletPath();
	        String queryString = req.getQueryString();
	        if (isMalicious(servletPath) || (queryString != null && isMalicious(queryString))) {
	            logAndBlock(resp, "Blocked request with potential attack", servletPath + "?" + queryString);
	            return;
	        }

	        Enumeration<String> paramNames = request.getParameterNames();
	        while (paramNames.hasMoreElements()) {
	            String param = paramNames.nextElement();
	            String value = request.getParameter(param);
	            if (value != null && isMalicious(value)) {
	                logAndBlock(resp, "Blocked parameter with potential attack", param + " = " + value);
	                return;
	            }
	        }

	        chain.doFilter(request, response);

	    } catch (Exception e) {
	        // Xử lý ngoại lệ
	        if (!resp.isCommitted()) {
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error occurred.");
	        } else {
	            // Nếu response đã cam kết, chỉ ghi log lỗi
	            System.err.println("Error in SecurityFilter: " + e.getMessage());
	        }
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

	private void logAndBlock(HttpServletResponse resp, String logMessage, String detail) throws IOException {
	    System.out.println(logMessage + ": " + detail);
	    if (!resp.isCommitted()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request blocked due to potential malicious content.");
	    } else {
	        // Nếu response đã cam kết, chỉ ghi log lỗi
	        System.err.println("Response already committed, unable to send error: " + logMessage + ": " + detail);
	    }
	}

}
