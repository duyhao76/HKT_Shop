package vn.HKT.services.impl;
import java.util.List;

import vn.HKT.daos.ICategoriesDao;
import vn.HKT.daos.impl.CategoriesDaoImpl;
import vn.HKT.entities.Categories;
import vn.HKT.services.ICategoriesService;

public class CategoriesServiceImpl implements ICategoriesService {

	ICategoriesDao cateDao = new CategoriesDaoImpl();
	
	@Override
	public List<Categories> findAllCategories() {
		return cateDao.findAllCategories();
	}

	@Override
	public Categories findCategoryById(String id) {
		return cateDao.findCategoryById(id);
	}

	@Override
	public void updateCategory(Categories category) {
		cateDao.updateCategory(category);
		
	}

	@Override
	public void insertCategory(Categories category) {
		cateDao.insertCategory(category);
	}

	@Override
	public Categories findCategoryForProduct(String id) {
		return cateDao.findCategoryForProduct(id);
	}

}
