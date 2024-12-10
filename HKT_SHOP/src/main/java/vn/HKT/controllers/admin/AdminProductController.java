package vn.HKT.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Enumeration;
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

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(urlPatterns = { "/admin/products", "/admin/product/add", "/admin/productview", "/admin/product/edit"})
public class AdminProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IProductService productService = new ProductServiceImpl();
	public ICategoriesService cateService = new CategoriesServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/products")) {
			List<Products> listProduct = productService.findAllProducts();
			List<Categories> listCategory = cateService.findAllCategories();

			req.setAttribute("listCategory", listCategory);
			req.setAttribute("listProduct", listProduct);
			req.getRequestDispatcher("/views/admin/AdminProductPage.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/productview")) {
			String id = req.getParameter("id");
			
			Products product = productService.findProductById(id);
			List<Categories> listCategory = cateService.findAllCategories();
			
			req.setAttribute("listCategory", listCategory);
			req.setAttribute("product", product);
			req.getRequestDispatcher("/views/admin/AdminProductEditPage.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("/admin/product/add")) {
			req.setCharacterEncoding("UTF-8");
			String productName = req.getParameter("productName");
			String categoryId = req.getParameter("categoryId");
			int unitPrice = Integer.parseInt(req.getParameter("unitPrice"));
			int Quantity = Integer.parseInt(req.getParameter("quantityInStock"));

			// Chuyển đổi java.sql.Date sang java.time.LocalDate
			LocalDate createdDate = Date.valueOf(req.getParameter("createdDate")).toLocalDate();
			LocalDate expiryDate = Date.valueOf(req.getParameter("expiryDate")).toLocalDate();

			String storageConditions = req.getParameter("storageConditions");

			Categories category = cateService.findCategoryForProduct(categoryId);

			if (category == null) {
				req.getSession().setAttribute("errorMessage", "Danh mục không tồn tại.");
				resp.sendRedirect(req.getContextPath() + "/admin/products");
				return;
			}

			Products product = new Products();
			product.setProductName(productName);
			product.setCategory(category);
			product.setUnitPrice(BigDecimal.valueOf(unitPrice));
			product.setQuantityInStock(Quantity);
			product.setCreatedDate(createdDate);
			product.setExpiryDate(expiryDate);
			product.setStorageConditions(storageConditions);
			product.setIsActive(true);

			// Xử lý img
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);

			String imgUrl = req.getParameter("imgPathUrl");
			if (imgUrl != null && !imgUrl.isEmpty()) {
				product.setImgPath(imgUrl);
			} else {
				Part part = req.getPart("imgPathFile");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// Upload file
					part.write(uploadPath + "/" + fname);

					// Ghi tên file vào data
					product.setImgPath(fname);
				}
			}
			productService.insertProduct(product);
			req.getSession().setAttribute("successMessage", "Đã thêm thành công!!!");
			resp.sendRedirect(req.getContextPath() + "/admin/products");
		}
		else if (url.contains("/admin/product/edit")) {
			req.setCharacterEncoding("UTF-8");
			
			Long productId = Long.valueOf(req.getParameter("productId"));
			String productOldId = req.getParameter("productId");
			String productName = req.getParameter("productName");
			String categoryid = req.getParameter("categoryId");
			
			LocalDate createdDate = Date.valueOf(req.getParameter("createdDate")).toLocalDate();
			LocalDate expiryDate = Date.valueOf(req.getParameter("expiryDate")).toLocalDate();
			
			Boolean status = Boolean.valueOf(req.getParameter("isActive"));
			String storageConditions = req.getParameter("storageConditions");
			
			String unitPriceStr = req.getParameter("unitPrice");
			BigDecimal unitPrice = new BigDecimal(unitPriceStr);
			
			int Quantity = Integer.parseInt(req.getParameter("quantityInStock"));
			
			Categories category = cateService.findCategoryForProduct(categoryid);

			if (category == null) {
				req.getSession().setAttribute("errorMessage", "Danh mục không tồn tại.");
				resp.sendRedirect(req.getContextPath() + "/admin/products");
				return;
			}
			
			Products product = new Products();
			product.setProductId(productId);
			product.setProductName(productName);
			product.setCategory(category);
			product.setUnitPrice(unitPrice);
			product.setQuantityInStock(Quantity);
			product.setCreatedDate(createdDate);
			product.setExpiryDate(expiryDate);
			product.setStorageConditions(storageConditions);
			product.setIsActive(status);
			
			Products oldProduct = productService.findProductById(productOldId);
			String fileOld = oldProduct.getImgPath();
			
			//Xử lý img
			 String fname = "";
			 String uploadPath = Constant.DIR;
			 File uploadDir = new File(uploadPath);
			 
			 String imgUrl = req.getParameter("imgPathUrl");
			 if (imgUrl != null && !imgUrl.isEmpty()) {
				 product.setImgPath(imgUrl);
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
					 product.setImgPath(fname);
				 }
				 else {
					 product.setImgPath(fileOld);
				 }
			 }
			 productService.updateProduct(product);
			 req.getSession().setAttribute("successMessage", "Đã sửa thành công!!!");
			 resp.sendRedirect(req.getContextPath() + "/admin/products");
		}
	}
}
