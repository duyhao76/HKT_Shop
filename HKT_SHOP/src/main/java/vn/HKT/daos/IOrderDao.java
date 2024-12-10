package vn.HKT.daos;

import java.util.List;
import vn.HKT.entities.Orders;

public interface IOrderDao {
	List<Orders> findAllOrders();
	Orders findOrderById(String id);
	void editStatusOrderById(String status, String id);
}
