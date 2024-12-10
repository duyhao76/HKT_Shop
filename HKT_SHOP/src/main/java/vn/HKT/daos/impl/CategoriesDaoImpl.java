package vn.HKT.daos.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.ICategoriesDao;
import vn.HKT.entities.Categories;

public class CategoriesDaoImpl implements ICategoriesDao{

	@Override
	public List<Categories> findAllCategories() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT new Categories(c.categoryId, c.categoryName, c.description, c.isActive, c.imgPath) FROM Categories c";
		TypedQuery<Categories> query = enma.createQuery(jpql, Categories.class);
		return query.getResultList();
	}

	@Override
	public Categories findCategoryById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT new Categories(c.categoryId, c.categoryName, c.description, c.isActive, c.imgPath) FROM Categories c WHERE c.categoryId = :id";
		TypedQuery<Categories> query = enma.createQuery(jpql, Categories.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public void updateCategory(Categories category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.merge(category);// update vào bảng
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insertCategory(Categories category) {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    
	    try {
	        trans.begin();
	        enma.persist(category); // Thêm mới vào bảng
	        trans.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        trans.rollback(); // Rollback nếu có lỗi
	        throw e; // Ném lại exception để xử lý ở cấp cao hơn
	    } finally {
	        enma.close(); // Đảm bảo đóng EntityManager
	    }
	}

	@Override
	public Categories findCategoryForProduct(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
	    String jpql = "SELECT c FROM Categories c WHERE c.categoryId = :id";
	    TypedQuery<Categories> query = enma.createQuery(jpql, Categories.class);
	    query.setParameter("id", id);
	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // Trả về null nếu không tìm thấy danh mục
	    }
	}
	
}
