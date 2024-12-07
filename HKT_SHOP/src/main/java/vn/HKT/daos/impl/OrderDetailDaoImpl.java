package vn.HKT.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IOrderDetailDao;
import vn.HKT.entities.OrderDetails;

public class OrderDetailDaoImpl implements IOrderDetailDao{

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<OrderDetails> findOrderDetailsById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		
		String jpql = "SELECT new OrderDetails(od.id, od.quantity, od.unitPrice, p.productName) " +
                "FROM OrderDetails od " +
                "JOIN od.product p " +
                "WHERE od.order.orderId = :orderId";
		
		TypedQuery<OrderDetails> query = enma.createQuery(jpql, OrderDetails.class);
		query.setParameter("orderId", id);
		
		return query.getResultList();
	}
	
	public static void main(String[] args) {
		IOrderDetailDao orderDetailsDAO = new OrderDetailDaoImpl();

        try {
            // Gọi hàm với một orderId cụ thể (thay "1" bằng orderId thực tế trong database của bạn)
            String orderId = "4";
            List<OrderDetails> orderDetails = orderDetailsDAO.findOrderDetailsById(orderId);

            // In kết quả
            System.out.println("Danh sách OrderDetails cho Order ID: " + orderId);
            for (OrderDetails detail : orderDetails) {
                System.out.println("Product Name: " + detail.getProductname());
                System.out.println("Quantity: " + detail.getQuantity());
                System.out.println("Unit Price: " + detail.getUnitPrice());
                System.out.println("------------------------");
            }

        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng EntityManager nếu cần
            // Nên đóng trong một phương thức riêng hoặc sử dụng try-with-resources
            try {
                JPAConfig.getEntityManager().close();
            } catch (Exception e) {
                System.out.println("Lỗi khi đóng EntityManager: " + e.getMessage());
            }
        }
	}
}
