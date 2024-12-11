package vn.HKT.daos.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.ICartDao;
import vn.HKT.entities.Cart;

public class CartDaoImpl implements ICartDao{

	@Override
	public Cart findCartByIdUser(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT * FROM Carts c WHERE c.user_id = :userId";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
		return query.getSingleResult();
	}

}
