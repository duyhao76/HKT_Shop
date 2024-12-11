package vn.HKT.controllers.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HKT.entities.Categories;
import vn.HKT.entities.Products;
import vn.HKT.services.ICategoryService;
import vn.HKT.services.IProductService;
import vn.HKT.services.impl.CategoryServiceImpl;
import vn.HKT.services.impl.ProductServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/user/home", "/user/search" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService = new ProductServiceImpl();
	private ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    Long userId = (Long) session.getAttribute("userId");		
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// Xử lý cho trang home
		if (url.contains("home")) {
			// Lấy tất cả danh mục
			List<Categories> categoryList = categoryService.findAll();
			req.setAttribute("categoryList", categoryList);

			// Lấy categoryId từ request (nếu có)
			String categoryIdParam = req.getParameter("categoryId");
			List<Products> productList;

			if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
				// Nếu categoryId được chọn, tìm sản phẩm theo categoryId
				Long categoryId = Long.parseLong(categoryIdParam);
				productList = productService.findByCategoryId(categoryId);
			} else {
				// Nếu không có categoryId, lấy tất cả sản phẩm
				productList = productService.findAll();
			}

			req.setAttribute("productList", productList);
			req.getRequestDispatcher("/views/user/UserHome.jsp").forward(req, resp);

		} else if (url.contains("search")) {
			// Xử lý tìm kiếm sản phẩm theo tên
			String keyword = req.getParameter("keyword");
			List<Products> productList;

			if (keyword != null && !keyword.isEmpty()) {
				// Nếu có từ khóa tìm kiếm, gọi service tìm kiếm sản phẩm theo tên
				productList = productService.searchByName(keyword);
			} else {
				// Nếu không có từ khóa tìm kiếm, lấy tất cả sản phẩm
				productList = productService.findAll();
			}
			// Lấy tất cả danh mục
			List<Categories> categoryList = categoryService.findAll();
			req.setAttribute("categoryList", categoryList);
			req.setAttribute("productList", productList);

			req.getRequestDispatcher("/views/user/UserSearch.jsp").forward(req, resp);
		}
	
	}
}
