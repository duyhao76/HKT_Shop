package vn.HKT.services;

import java.util.List;

import vn.HKT.entities.CartItem;
import vn.HKT.entities.Categories;

public interface ICartService {
	List<CartItem> findByUserId(long userId);

}
