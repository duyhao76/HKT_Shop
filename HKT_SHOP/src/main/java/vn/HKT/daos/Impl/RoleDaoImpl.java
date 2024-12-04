package vn.HKT.daos.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IRoleDao;
import vn.HKT.entities.Roles;

public class RoleDaoImpl implements IRoleDao {

    private EntityManager entityManager;

    public RoleDaoImpl() {
        this.entityManager = JPAConfig.getEntityManager();
    }

    @Override
    public void insert(Roles role) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(role);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Roles findById(Long id) {
        return entityManager.find(Roles.class, id);
    }

    @Override
    public Roles findByRoleName(String roleName) {
        String jpql = "SELECT r FROM Roles r WHERE r.roleName = :roleName";
        try {
            return entityManager.createQuery(jpql, Roles.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Không tìm thấy hoặc lỗi
        }
    }

    public static void main(String[] args) {
        RoleDaoImpl roleDao = new RoleDaoImpl();

//        // Thêm vai trò User
//        Roles userRole = Roles.builder()
//                .roleName("user")
//                .description("Standard user role with limited permissions")
//                .build();
//        roleDao.insert(userRole);
//        System.out.println("Inserted role: user");
//
//        // Thêm vai trò Admin
//        Roles adminRole = Roles.builder()
//                .roleName("admin")
//                .description("Administrator role with full permissions")
//                .build();
//        roleDao.insert(adminRole);
//        System.out.println("Inserted role: admin");

        // Kiểm tra tìm kiếm theo tên vai trò
        Roles foundRole = roleDao.findByRoleName("admin");
        if (foundRole != null) {
            System.out.println("Found role: " + foundRole.getRoleName());
        } else {
            System.out.println("Role not found!");
        }
        
     // Kiểm tra tìm kiếm theo id
        Long searchId = 1L; // Thay bằng id bạn muốn kiểm tra
        Roles foundRoleById = roleDao.findById(searchId);
        if (foundRoleById != null) {
            System.out.println("Found role by id: " + foundRoleById.getRoleName());
        } else {
            System.out.println("Role not found by id!");
        }
        
    }
}
