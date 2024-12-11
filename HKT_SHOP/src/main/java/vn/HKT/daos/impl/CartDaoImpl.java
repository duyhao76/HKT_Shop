package vn.HKT.daos.impl;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.ICartDao;
import vn.HKT.entities.CartItem;

public class CartDaoImpl implements ICartDao {
    @Override
    public List<CartItem> findByUserId(long userId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<CartItem> query = enma.createQuery("SELECT c FROM CartItem c", CartItem.class);
            query.setParameter("userId", userId);
            List<CartItem> items = query.getResultList();
            return items != null ? items : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            enma.close();
        }
    }

}
