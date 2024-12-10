package vn.HKT.daos;

import java.util.List;

import vn.HKT.entities.Products;

public interface IProductDao {
	List<Products> findProductByIdCategory(String id);
	
	List<Products> findAllProducts();
	
	void insertProduct(Products product);
	
	Products findProductById(String id);
	
	void updateProduct(Products product);

	Products findById(Long productId);

    List<Products> findAll();

    List<Products> searchByName(String keyword);
	
	List<Products> findByCategoryId(Long categoryId);
}
