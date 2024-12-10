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

}
