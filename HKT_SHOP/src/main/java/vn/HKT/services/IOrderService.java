package vn.HKT.services;

import java.util.List;

import vn.HKT.entities.Orders;

public interface IOrderService {
	List<Orders> findAllOrders();
	Orders findOrderById(String id);
}
