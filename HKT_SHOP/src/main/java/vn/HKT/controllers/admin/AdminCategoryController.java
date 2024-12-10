package vn.HKT.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.HKT.entities.Categories;
import vn.HKT.entities.Products;
import vn.HKT.services.ICategoriesService;
import vn.HKT.services.IProductService;
import vn.HKT.services.impl.CategoriesServiceImpl;
import vn.HKT.services.impl.ProductServiceImpl;
import vn.HKT.utils.Constant;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)

@WebServlet (urlPatterns = {"/admin/categories", "/admin/categoryview", "/admin/category/edit", "/admin/category/add"})
public class AdminCategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public ICategoriesService cateService = new CategoriesServiceImpl();
	public IProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/categories")) {
			List<Categories> listCategory = cateService.findAllCategories();
			
			req.setAttribute("listCategory", listCategory);
			req.getRequestDispatcher("/views/admin/AdminCategoryPage.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/categoryview")) {
			String id = req.getParameter("id");
			
			Categories category = cateService.findCategoryById(id);
			List<Products> listProduct = productService.findProductByIdCategory(id);
			
			req.setAttribute("category", category);
			req.setAttribute("listProduct", listProduct);
			req.getRequestDispatcher("/views/admin/AdminCategoryEditPage.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/category/edit")) {
			 req.setCharacterEncoding("UTF-8");
			
			 Long categoryid = Long.valueOf(req.getParameter("categoryId"));
			 String idold = req.getParameter("categoryId");
			 String categoryname = req.getParameter("categoryName");
			 Boolean status = Boolean.valueOf(req.getParameter("isActive"));
			 String decription = req.getParameter("decription");
			 
			 Categories category = new Categories();
			 category.setCategoryId(categoryid);
			 category.setCategoryName(categoryname);
			 category.setIsActive(status);
			 category.setDescription(decription);
			 
			 //Lưu ảnh cũ
			 Categories oldCategory = cateService.findCategoryById(idold);
			 String fileOld = oldCategory.getImgPath();
			 
			 //Xử lý img
			 String fname = "";
			 String uploadPath = Constant.DIR;
			 File uploadDir = new File(uploadPath);
			 
			 String imgUrl = req.getParameter("imgPathUrl");
			 if (imgUrl != null && !imgUrl.isEmpty()) {
				 category.setImgPath(imgUrl);
			 }
			 else {
				 Part part = req.getPart("imgPathFile");
				 if (part.getSize() > 0) {
					 String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					 
					 //Đổi tên file
					 int index = filename.lastIndexOf(".");
					 String ext = filename.substring(index + 1);
					 fname = System.currentTimeMillis() + "." + ext;
					 
					 //Upload file
					 part.write(uploadPath + "/" + fname);
					 
					 //Ghi tên file vào data
					 category.setImgPath(fname);
				 }
				 else {
					 category.setImgPath(fileOld);
				 }
			 }
			 cateService.updateCategory(category);
			 req.getSession().setAttribute("successMessage", "Đã sửa thành công!!!");
			 resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		else if (url.contains("/admin/category/add")) {
			req.setCharacterEncoding("UTF-8");
			
			String cateName = req.getParameter("categoryName");
			String description = req.getParameter("description");
			Boolean isActive = true;
			Categories category = new Categories();
			
			category.setCategoryName(cateName);
			category.setDescription(description);
			category.setIsActive(isActive);
			
			//Xử lý img
			 String fname = "";
			 String uploadPath = Constant.DIR;
			 File uploadDir = new File(uploadPath);
			 
			 String imgUrl = req.getParameter("imgPathUrl");
			 if (imgUrl != null && !imgUrl.isEmpty()) {
				 category.setImgPath(imgUrl);
			 }
			 else {
				 Part part = req.getPart("imgPathFile");
				 if (part.getSize() > 0) {
					 String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					 
					 //Đổi tên file
					 int index = filename.lastIndexOf(".");
					 String ext = filename.substring(index + 1);
					 fname = System.currentTimeMillis() + "." + ext;
					 
					 //Upload file
					 part.write(uploadPath + "/" + fname);
					 
					 //Ghi tên file vào data
					 category.setImgPath(fname);
				 }
			 }
			 cateService.insertCategory(category);
			 req.getSession().setAttribute("successMessage", "Đã thêm thành công!!!");
			 resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
