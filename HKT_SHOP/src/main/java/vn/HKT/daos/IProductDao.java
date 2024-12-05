package vn.HKT.daos;

import java.util.List;

import vn.HKT.entities.Products;

public interface IProductDao {
	Products findById(Long productId);
    List<Products> findAll();
    List<Products> searchByName(String keyword);
	List<Products> findByCategoryId(Long categoryId);
}
