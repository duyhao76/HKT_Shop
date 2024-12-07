package vn.HKT.services;

import java.util.List;

import vn.HKT.entities.OrderDetails;

public interface IOrderDetailService {
	List<OrderDetails> findOrderDetailsById(String id);
}
