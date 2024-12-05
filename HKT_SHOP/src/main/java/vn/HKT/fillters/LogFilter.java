package vn.HKT.fillters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/*"})
public class LogFilter implements Filter {

    public LogFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("LogFilter init!");
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        //In ra lịch sử truy cập các URL
        System.out.println("#INFO - ServletPath: " + servletPath + ", URL =" + req.getRequestURL());
        
        // Cho phép request được đi tiếp (vượt qua Filter này)
        chain.doFilter(request, response);
    }
}
