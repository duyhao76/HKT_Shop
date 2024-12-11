package vn.HKT.daos;

import java.util.List;
import vn.HKT.entities.CartItem;

public interface ICartDao {
	List<CartItem> findByUserId(long userId);
}
