package vn.HKT.services.impl;

import java.util.List;
import vn.HKT.daos.ICategoryDao;
import vn.HKT.daos.impl.CategoryDaoImpl;
import vn.HKT.entities.Categories;
import vn.HKT.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	private ICategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public List<Categories> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Categories findById(Long categoryId) {
		return categoryDao.findById(categoryId);
	}

}
