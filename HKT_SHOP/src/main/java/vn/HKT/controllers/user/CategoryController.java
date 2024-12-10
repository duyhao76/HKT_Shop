package vn.HKT.controllers.user;

import java.io.IOException;
import java.util.Comparator;
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
@WebServlet(urlPatterns = { "/user/category" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProductService productService = new ProductServiceImpl();
	private ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("category")) {
			List<Products> productList = productService.findAll(); 
	        List<Categories> categoryList = categoryService.findAll();
	     // Kiểm tra nếu có categoryId trong URL, lấy danh mục theo categoryId
	        String categoryIdParam = req.getParameter("categoryId");
	        Categories category = null;
	        if (categoryIdParam != null) {
	            try {
	                Long categoryId = Long.parseLong(categoryIdParam);
	                // Lọc sản phẩm theo categoryId
	                productList = productService.findByCategoryId(categoryId);
	                category = categoryService.findById(categoryId);
	            } catch (NumberFormatException e) {
	                // Nếu không phải số hợp lệ, không thay đổi danh sách sản phẩm
	            }
	        }
	        
	     // Kiểm tra tham số sortBy và sắp xếp danh sách sản phẩm
	        String sortByParam = req.getParameter("sortBy");
	        if (sortByParam != null) {
	            try {
	                int sortBy = Integer.parseInt(sortByParam);
	                if (sortBy == 2) {
	                    // Sắp xếp theo giá (tăng dần)
	                    productList.sort(Comparator.comparing(Products::getPrice));  // Tăng dần theo giá
	                }
	                // Nếu cần sắp xếp theo giảm dần thì thêm phần xử lý sau
	            } catch (NumberFormatException e) {
	                // Nếu sortBy không hợp lệ, không thay đổi danh sách sản phẩm
	                e.printStackTrace();
	            }
	        }
	        
	     // Lưu danh sách sản phẩm và danh mục vào request để truyền xuống JSP
	        req.setAttribute("productList", productList);
	        req.setAttribute("categoryList", categoryList);
	        req.setAttribute("category", category);  
	        req.getRequestDispatcher("/views/user/UserCategory.jsp").forward(req, resp);
		}
	}
}