package vn.HKT.daos.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IUserDao;
import vn.HKT.entities.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {

	private EntityManager entityManager;

	public UserDaoImpl() {
		this.entityManager = JPAConfig.getEntityManager();
	}

	@Override
	public Users findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT u FROM Users u " + "LEFT JOIN FETCH u.role r " + "WHERE u.username = :username";

		TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
		query.setParameter("username", username);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("Không tìm thấy user với username: " + username);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void insert(Users user) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public Users findByEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT u FROM Users u " + "LEFT JOIN FETCH u.role r " + "WHERE u.email = :email";

		TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
		query.setParameter("email", email);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("Không tìm thấy user với email: " + email);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updatePasswordByEmail(Users user) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Users existingUser = findByEmail(user.getEmail());
			if (existingUser != null) {
				existingUser.setPassword(user.getPassword());
				entityManager.merge(existingUser);
			} else {
				System.err.println("Không tìm thấy user với email: " + user.getEmail());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive())
				transaction.rollback();
		}
	}

	@Override
	public boolean isResetTokenValid(String token) {
		String jpql = "SELECT u.expiry FROM Users u WHERE u.token = :token";
		try {
			TypedQuery<LocalDateTime> query = entityManager.createQuery(jpql, LocalDateTime.class);
			query.setParameter("token", token);

			LocalDateTime expiryDate = query.getSingleResult();
			LocalDateTime now = LocalDateTime.now().withNano(0); // Bỏ phần mili giây

			return expiryDate != null && expiryDate.isAfter(now);
		} catch (Exception e) {
			System.err.println("Token không hợp lệ hoặc không tồn tại: " + token);
			return false;
		}
	}

	@Override
	public void updatePasswordByToken(String token, String hashedPassword) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			String jpql = "SELECT u FROM Users u WHERE u.token = :token";
			TypedQuery<Users> query = entityManager.createQuery(jpql, Users.class);
			query.setParameter("token", token);

			Users user = query.getSingleResult();
			if (user != null) {
				user.setPassword(hashedPassword);
				user.setToken(null); // Xóa token sau khi sử dụng
				user.setExpiry(null);
				entityManager.merge(user);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void updateResetToken(String email, String token, Timestamp expiry) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Users user = findByEmail(email);
			if (user != null) {
				user.setToken(token);
				user.setExpiry(expiry.toLocalDateTime());
				entityManager.merge(user);
			} else {
				System.err.println("Không tìm thấy user với email: " + email);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive())
				transaction.rollback();
		}
	}

	@Override
	public List<Users> findAll() {
	    EntityManager entityManager = JPAConfig.getEntityManager();
	    List<Users> users = new ArrayList<>();

	    try {
	        // Sử dụng LEFT JOIN FETCH nhưng chỉ nạp role mà không nạp users trong Roles
	        String jpql = "SELECT u FROM Users u LEFT JOIN FETCH u.role r";
	        users = entityManager.createQuery(jpql, Users.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace(); // Ghi log lỗi nếu xảy ra ngoại lệ
	    } finally {
	        if (entityManager != null) {
	            entityManager.close();
	        }
	    }
	    return users;
	}

	@Override
	public void update(Users user) {
	    EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();
	        Users existingUser = entityManager.find(Users.class, user.getUserId());
	        if (existingUser != null) {
	            // Chỉ cập nhật các trường không liên quan đến role
	            existingUser.setUsername(user.getUsername() != null ? user.getUsername() : existingUser.getUsername());
	            existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
	            existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
	            existingUser.setLastLogin(user.getLastLogin() != null ? user.getLastLogin() : existingUser.getLastLogin());
	            existingUser.setToken(user.getToken() != null ? user.getToken() : existingUser.getToken());
	            existingUser.setExpiry(user.getExpiry() != null ? user.getExpiry() : existingUser.getExpiry());

	            entityManager.merge(existingUser);
	            System.out.println("User updated successfully: " + existingUser.getUsername());
	        } else {
	            System.err.println("User not found with ID: " + user.getUserId());
	        }
	        transaction.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw new RuntimeException("Lỗi khi cập nhật người dùng: " + e.getMessage());
	    }
	}

	@Override
	public Users findById(Long userId) {
	    try {
	        return entityManager.find(Users.class, userId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // Trả về null nếu không tìm thấy hoặc có lỗi
	    }
	}
	
	@Override
	public List<Users> findAllUsers() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT u FROM Users u JOIN FETCH u.role";
		TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
		return query.getResultList();
	}

	@Override
	public Users findUserById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT u FROM Users u JOIN FETCH u.role WHERE u.userId = :userId";
		TypedQuery<Users> query = enma.createQuery(jpql, Users.class);
		query.setParameter("userId", id);
		return query.getSingleResult();
	}

	@Override
	public void editUserRoleById(String role, String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		enma.getTransaction().begin();
		
		String jpql = "UPDATE Users u SET u.role.roleId = :roleId WHERE u.userId = :userId";
		
		Query query = enma.createQuery(jpql);
		query.setParameter("roleId", role);
        query.setParameter("userId", id);
        query.executeUpdate();
        
        enma.getTransaction().commit();
	    enma.close();
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
//	    // Thêm user mới
//	    Users user = Users.builder()
//	            .username("johndoe")
//	            .password("password123")
//	            .email("john.doe@example.com")
//	            .build();
//	    userDao.insert(user);
//	    System.out.println("Inserted user: " + user.getUsername());

//	 // Tìm user theo username
//	    Users foundUser = userDao.findByUsername("johndoe");
//	    if (foundUser != null) {
//	        System.out.println("Username: " + foundUser.getUsername());
//	        System.out.println("Role: " + foundUser.getRole().getRoleName());
//	    } else {
//	        System.out.println("User không tồn tại.");
//	    }

//		// Tìm user theo email
//		Users foundUserByEmail = userDao.findByEmail("john.doe@example.com");
//		if (foundUserByEmail != null) {
//			System.out.println("Email: " + foundUserByEmail.getEmail());
//			System.out.println("Role: " + foundUserByEmail.getRole().getRoleName());
//		} else {
//			System.out.println("User không tồn tại.");
//		}

//	    // Cập nhật mật khẩu qua email
//	    if (foundUserByEmail != null) {
//	        foundUserByEmail.setPassword("newpassword123");
//	        userDao.updatePasswordByEmail(foundUserByEmail);
//	        System.out.println("Updated password for email: " + foundUserByEmail.getEmail());
//	    }

//		// Cập nhật token reset mật khẩu
//		String resetToken = "reset-token-123";
//		userDao.updateResetToken("john.doe@example.com", resetToken,
//				Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
//		System.out.println("Reset token updated for user: " + foundUserByEmail.getEmail());
//
//		// Kiểm tra token reset hợp lệ
//		boolean isValid = userDao.isResetTokenValid(resetToken);
//		System.out.println("Is reset token valid: " + isValid);
//
//		// Đổi mật khẩu bằng token
//		userDao.updatePasswordByToken(resetToken, "new-secure-password");
//		System.out.println("Password updated via token for user: " + foundUserByEmail.getEmail());
		
		// Gọi phương thức findAll
		try {
	        // Gọi phương thức findAll() từ DAO
	        List<Users> users = userDao.findAll();

	        if (users != null && !users.isEmpty()) {
	            System.out.println("Danh sách người dùng:");
	            for (Users user : users) {
	                // In thông tin từng người dùng
	                System.out.println("ID: " + user.getUserId());
	                System.out.println("Username: " + user.getUsername());
	                System.out.println("Created Date: " + user.getCreatedDate());
	                System.out.println("Last Login: " + user.getLastLogin());
	                System.out.println("Email: " + user.getEmail());
	                System.out.println("----------------------");
	            }
	        } else {
	            System.out.println("Không có dữ liệu user.");
	        }
	    } catch (Exception e) {
	        // Ghi log lỗi nếu xảy ra ngoại lệ
	        System.err.println("Lỗi khi truy vấn danh sách người dùng: " + e.getMessage());
	        e.printStackTrace();
	    }
		
	}


}
