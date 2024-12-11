package vn.HKT.services.impl;

import java.util.List;
import vn.HKT.entities.CartItem;
import vn.HKT.services.ICartService;
import vn.HKT.daos.ICartDao;
import vn.HKT.daos.impl.CartDaoImpl;

public class CartServiceImpl implements ICartService {

    private ICartDao cartDao = new CartDaoImpl(); 
    @Override
    public List<CartItem> findByUserId(long userId) {
    	
        return cartDao.findByUserId(userId); 
    }
    
}
