package vn.HKT.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Order;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IOrderDao;
import vn.HKT.entities.Orders;

public class OrderDaoImpl implements IOrderDao{

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<Orders> findAllOrders() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT o, u.username FROM Orders o " +
	              "JOIN o.user u " +
	              "ORDER BY o.orderId DESC";
		TypedQuery<Tuple> query = enma.createQuery(jpql, Tuple.class);
		List<Tuple> results = query.getResultList();
		
		List<Orders> orderList = new ArrayList<>();
		
		for (Tuple result : results) {
			Orders order = result.get(0, Orders.class);
			String username = result.get(1, String.class);
			
			order.setUsername(username);
			orderList.add(order);
		}
		
		return orderList;
	}

	@Override
	public Orders findOrderById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT o, u.username " +
	              "FROM Orders o " +
	              "JOIN o.user u " +
	              "WHERE o.orderId = :orderId";
		
		TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
		query.setParameter("orderId", id);
		
		Object[] result = query.getSingleResult();
		Orders order = (Orders) result[0];
		String username = (String) result[1];
		
		order.setUsername(username);

		return order;
	}

	@Override
	public void editStatusOrderById(String status, String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		enma.getTransaction().begin();
		
		String jpql = "UPDATE Orders o SET o.status = :status WHERE o.orderId = :orderId";
		Query query = enma.createQuery(jpql);
		
		query.setParameter("status", status);
	    query.setParameter("orderId", id);
	    query.executeUpdate();
	    
	    enma.getTransaction().commit();
	    enma.close();
	}
}
