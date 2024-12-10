package vn.HKT.services;

import java.util.List;

import vn.HKT.entities.Categories;

public interface ICategoryService {
	List<Categories> findAll();

	Categories findById(Long categoryId);
}
