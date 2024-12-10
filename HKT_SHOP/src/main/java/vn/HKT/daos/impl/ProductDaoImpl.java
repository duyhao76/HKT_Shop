package vn.HKT.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IProductDao;
import vn.HKT.entities.Products;

public class ProductDaoImpl implements IProductDao{

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<Products> findProductByIdCategory(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT new Products(p.productId, p.productName, p.unitPrice, p.quantityInStock, " +
                "p.expiryDate, p.storageConditions, p.createdDate, p.isActive, p.imgPath) " +
                "FROM Products p WHERE p.category.categoryId = :categoryId";
        TypedQuery<Products> query = enma.createQuery(jpql, Products.class);
        query.setParameter("categoryId", id);
        return query.getResultList();
	}

	@Override
	public List<Products> findAllProducts() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT p FROM Products p";
		TypedQuery<Products> query = enma.createQuery(jpql, Products.class);
		return query.getResultList();
	}

	@Override
	public void insertProduct(Products product) {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    
	    try {
	        trans.begin();
	        enma.persist(product); // Thêm mới vào bảng
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
	public Products findProductById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT p FROM Products p WHERE p.productId = :id";
		TypedQuery<Products> query = enma.createQuery(jpql, Products.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public void updateProduct(Products product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.merge(product);// update vào bảng
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}
