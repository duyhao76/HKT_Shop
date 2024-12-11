package vn.HKT.services;

import vn.HKT.entities.Cart;

public interface ICartService {
	Cart findCartByIdUser (String id);
}
