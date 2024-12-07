package vn.HKT.daos;

import java.util.List;

import vn.HKT.entities.OrderDetails;

public interface IOrderDetailDao {
	List<OrderDetails> findOrderDetailsById(String id);
}
