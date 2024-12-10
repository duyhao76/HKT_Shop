package vn.HKT.controllers.guest;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HKT.entities.Categories;
import vn.HKT.entities.Products;
import vn.HKT.services.ICategoryService;
import vn.HKT.services.IProductService;
import vn.HKT.services.impl.CategoryServiceImpl;
import vn.HKT.services.impl.ProductServiceImpl;
@WebServlet(urlPatterns = { "/guest/detail"})
public class DetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProductService productService = new ProductServiceImpl();
	private ICategoryService categoryService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("detail")) {
			List<Categories> categoryList = categoryService.findAll();
			req.setAttribute("categoryList", categoryList);
			String productIdParam = req.getParameter("productId");
			if (productIdParam != null) {
                try {
                    Long productId = Long.parseLong(productIdParam);
                    Products product = productService.findById(productId);
                    if (product != null) {
                        // Lưu thông tin sản phẩm vào request để gửi ra view
                        req.setAttribute("product", product);
                        // Forward request đến trang JSP để hiển thị chi tiết sản phẩm
                        req.getRequestDispatcher("/views/guest/GuestDetail.jsp").forward(req, resp);
                    } else {
                    	// Nếu không tìm thấy sản phẩm, set thông báo lỗi vào request
                        req.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
                        req.getRequestDispatcher("/views/guest/GuestDetail.jsp").forward(req, resp);
                    }
                } catch (NumberFormatException e) {
                	// Nếu productId không hợp lệ, set thông báo lỗi vào request
                    req.setAttribute("errorMessage", "ID sản phẩm không hợp lệ.");
                    req.getRequestDispatcher("/views/guest/GuestDetail.jsp").forward(req, resp);
                }
            } else {
            	// Nếu không có productId trong URL, set thông báo lỗi vào request
                req.setAttribute("errorMessage", "Không có ID sản phẩm.");
                req.getRequestDispatcher("/views/guest/GuestDetail.jsp").forward(req, resp);
            }
		}
	}
}
