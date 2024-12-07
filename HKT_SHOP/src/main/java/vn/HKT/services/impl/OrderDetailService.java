package vn.HKT.services.impl;

import java.util.List;

import vn.HKT.daos.IOrderDetailDao;
import vn.HKT.daos.impl.OrderDetailDaoImpl;
import vn.HKT.entities.OrderDetails;
import vn.HKT.services.IOrderDetailService;

public class OrderDetailService implements IOrderDetailService{
	
	IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
	
	@Override
	public List<OrderDetails> findOrderDetailsById(String id) {
		return orderDetailDao.findOrderDetailsById(id);
	}

}
