package vn.HKT.services.impl;

import java.util.List;

import vn.HKT.daos.IProductDao;
import vn.HKT.daos.impl.ProductDaoImpl;
import vn.HKT.entities.Products;
import vn.HKT.services.IProductService;

public class ProductServiceImpl implements IProductService{

	IProductDao productDao = new ProductDaoImpl();
	
	@Override
	public List<Products> findProductByIdCategory(String id) {
		return productDao.findProductByIdCategory(id);
	}

	@Override
	public List<Products> findAllProducts() {
		return productDao.findAllProducts();
	}

	@Override
	public void insertProduct(Products product) {
		productDao.insertProduct(product);
		
	}

	@Override
	public Products findProductById(String id) {
		return productDao.findProductById(id);
	}

	@Override
	public void updateProduct(Products product) {
		productDao.updateProduct(product);
		
	}
}
