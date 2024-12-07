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
		
		String jpql = "SELECT od, p.productName " +
	              "FROM OrderDetails od " +
	              "JOIN od.product p " +
	              "WHERE od.order.orderId = :orderId";
		
		TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
		query.setParameter("orderId", id);
		
		List<Object[]> results = query.getResultList();
		List<OrderDetails> list = new ArrayList<>();
		
		for (Object[] result : results) {
			OrderDetails orderDetail = (OrderDetails) result[0];
			String productName = (String) result[1];
			
			orderDetail.setProductname(productName);
			orderDetail.setOrder(null);
			
			list.add(orderDetail);
		}
		return list;
	}

}
