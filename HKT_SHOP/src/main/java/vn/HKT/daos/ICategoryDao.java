package vn.HKT.daos;

import java.util.List;
import vn.HKT.entities.Categories;

public interface ICategoryDao {
	List<Categories> findAll();
}
