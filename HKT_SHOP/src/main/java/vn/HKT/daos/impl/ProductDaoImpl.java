package vn.HKT.daos.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IProductDao;
import vn.HKT.entities.Products;

public class ProductDaoImpl implements IProductDao {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
    public Products findById(Long productId) {
        EntityManager enma = JPAConfig.getEntityManager();
        Products product = enma.find(Products.class, productId);
        enma.close();
        return product;
    }

	@Override
    public List<Products> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Products> query = enma.createQuery("SELECT p FROM Products p", Products.class);
        List<Products> products = query.getResultList();
        enma.close();
        return products;
    }

	@Override
	public List<Products> searchByName(String keyword) {
		EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM Products p WHERE p.productName LIKE :keyword"; // Tìm sản phẩm theo tên
        TypedQuery<Products> query = em.createQuery(jpql, Products.class);
        query.setParameter("keyword", "%" + keyword + "%"); // Tìm kiếm với từ khóa
        return query.getResultList();
	}
	@Override
	public List<Products> findByCategoryId(Long categoryId) {
	    EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT p FROM Products p WHERE p.category.categoryId = :categoryId"; // Lọc sản phẩm theo categoryId
	    TypedQuery<Products> query = em.createQuery(jpql, Products.class);
	    query.setParameter("categoryId", categoryId); // Gán giá trị categoryId vào câu truy vấn
	    List<Products> products = query.getResultList();
	    em.close();
	    return products;
	}

}
