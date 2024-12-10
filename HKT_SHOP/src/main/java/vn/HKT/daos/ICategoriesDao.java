package vn.HKT.daos;

import java.util.List;

import vn.HKT.entities.Categories;

public interface ICategoriesDao {
	List<Categories> findAllCategories();
	
	Categories findCategoryById(String id);
	
	void updateCategory(Categories category);
	
	void insertCategory(Categories category);
	
	Categories findCategoryForProduct(String id);
}
