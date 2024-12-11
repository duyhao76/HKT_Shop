package vn.HKT.daos;

import vn.HKT.entities.Cart;

public interface ICartDao {
	Cart findCartByIdUser (String id);
}
