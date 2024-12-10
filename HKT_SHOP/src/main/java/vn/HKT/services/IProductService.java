package vn.HKT.services;

import java.util.List;

import vn.HKT.entities.Products;

public interface IProductService {
	List<Products> findProductByIdCategory(String id);
	
	List<Products> findAllProducts();
	
	void insertProduct(Products product);
	
	Products findProductById(String id);
	
	void updateProduct(Products product);

	List<Products> findAll();    // Lấy tất cả sản phẩm

    Products findById(Long id);

    List<Products> searchByName(String keyword);
	
	List<Products> findByCategoryId(Long categoryId);
}
