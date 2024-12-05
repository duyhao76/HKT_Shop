package vn.HKT.services.impl;

import java.util.List;

import vn.HKT.daos.IProductDao;
import vn.HKT.daos.impl.ProductDaoImpl;
import vn.HKT.entities.Products;
import vn.HKT.services.IProductService;

public class ProductServiceImpl implements IProductService {
	public IProductDao productDao = new ProductDaoImpl();

	@Override
	public List<Products> findAll() {
		// Trả về tất cả các sản phẩm
		return productDao.findAll();
	}

	@Override
	public Products findById(Long id) {
		// Tìm sản phẩm theo ID
		return productDao.findById(id);
	}

	@Override
	public List<Products> searchByName(String keyword) {
		return productDao.searchByName(keyword);
	}
	@Override
    public List<Products> findByCategoryId(Long categoryId) {
        return productDao.findByCategoryId(categoryId);
    }
}
