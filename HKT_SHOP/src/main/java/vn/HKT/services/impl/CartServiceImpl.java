package vn.HKT.services.impl;

import vn.HKT.daos.ICartDao;
import vn.HKT.daos.impl.CartDaoImpl;
import vn.HKT.entities.Cart;
import vn.HKT.services.ICartService;

public class CartServiceImpl implements ICartService{

	ICartDao cartDao = new CartDaoImpl();
	
	@Override
	public Cart findCartByIdUser(String id) {
		return cartDao.findCartByIdUser(id);
	}

}
