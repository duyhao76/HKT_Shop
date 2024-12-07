package vn.HKT.services.impl;

import java.util.List;

import vn.HKT.daos.IOrderDao;
import vn.HKT.daos.impl.OrderDaoImpl;
import vn.HKT.entities.Orders;
import vn.HKT.services.IOrderService;

public class OrderServiceImpl implements IOrderService{

	IOrderDao orderDao = new OrderDaoImpl();
	
	@Override
	public List<Orders> findAllOrders() {
		return orderDao.findAllOrders();
	}

	@Override
	public Orders findOrderById(String id) {
		return orderDao.findOrderById(id);
	}

}
