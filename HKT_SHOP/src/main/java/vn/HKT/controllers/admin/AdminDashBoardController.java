package vn.HKT.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HKT.entities.Orders;
import vn.HKT.services.IOrderService;
import vn.HKT.services.impl.OrderServiceImpl;

@WebServlet (urlPatterns = {"/admin/dashboard"})
public class AdminDashBoardController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public IOrderService orderService = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/dashboard")) {
			List<Orders> listOrder = orderService.findAllOrders();
			
			req.setAttribute("listOrder", listOrder);
			req.getRequestDispatcher("/views/admin/AdminDashBoardPage.jsp").forward(req, resp);
		}
	}
}
