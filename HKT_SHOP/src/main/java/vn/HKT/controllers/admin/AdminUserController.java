package vn.HKT.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HKT.entities.Users;
import vn.HKT.services.IUserService;
import vn.HKT.services.impl.UserServiceImpl;

@WebServlet (urlPatterns = {"/admin/users", "/admin/userview", "/admin/user/edit"})
public class AdminUserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/users")) {
			List<Users> listUser = userService.findAllUsers();
			
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("/views/admin/AdminUserPage.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/userview")) {
			String id = req.getParameter("id");
			
			Users user = userService.findUserById(id);
			req.setAttribute("user", user);
			
			req.getRequestDispatcher("/views/admin/AdminUserEditPage.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/user/edit")) {
			String id = req.getParameter("userId");
			String role = req.getParameter("role");
			
			userService.editUserRoleById(role, id);
			req.getSession().setAttribute("successMessage", "Đã sửa thành công!!!");
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}
}
