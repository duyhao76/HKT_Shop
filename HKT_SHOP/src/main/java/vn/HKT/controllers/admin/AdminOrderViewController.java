package vn.HKT.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HKT.entities.OrderDetails;
import vn.HKT.entities.Orders;
import vn.HKT.services.IOrderDetailService;
import vn.HKT.services.IOrderService;
import vn.HKT.services.impl.OrderDetailService;
import vn.HKT.services.impl.OrderServiceImpl;

@WebServlet (urlPatterns = {"/admin/orders", "/admin/order/edit"})
public class AdminOrderViewController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public IOrderService orderService = new OrderServiceImpl();
	public IOrderDetailService orderDetailService = new OrderDetailService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/orders")) {
			String id = req.getParameter("id");
			
			Orders order = orderService.findOrderById(id);
			List<OrderDetails> listOrderDetail = orderDetailService.findOrderDetailsById(id);
			
			req.setAttribute("order", order);
			req.setAttribute("listOrderDetail", listOrderDetail);
			req.getRequestDispatcher("/views/admin/AdminOrderViewPage.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if (url.contains("/admin/order/edit")) {
			String id = req.getParameter("orderId");
			String status = req.getParameter("status");
			
			orderService.editStatusOrderById(status, id);
			req.getSession().setAttribute("successMessage", "Đã sửa thành công!!!");
			resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
		}
	}
}
