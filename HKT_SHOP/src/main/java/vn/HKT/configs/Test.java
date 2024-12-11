package vn.HKT.configs;

import jakarta.persistence.EntityManager;
import vn.HKT.configs.JPAConfig;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = JPAConfig.getEntityManager();
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
