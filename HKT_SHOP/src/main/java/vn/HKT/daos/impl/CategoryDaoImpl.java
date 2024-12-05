package vn.HKT.daos.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.ICategoryDao;
import vn.HKT.entities.Categories;

public class CategoryDaoImpl implements ICategoryDao {

	@Override
	public List<Categories> findAll() {
		// Tạo EntityManager từ JPAConfig
        EntityManager enma = JPAConfig.getEntityManager();
        
        // Sử dụng JPQL để truy vấn tất cả các danh mục
        String jpql = "SELECT c FROM Categories c";
        TypedQuery<Categories> query = enma.createQuery(jpql, Categories.class);
        // Trả về kết quả dưới dạng danh sách các Category
        return query.getResultList();
	}
}
