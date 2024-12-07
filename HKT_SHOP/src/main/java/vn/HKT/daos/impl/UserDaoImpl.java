package vn.HKT.daos.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IUserDao;
import vn.HKT.entities.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserDaoImpl implements IUserDao {

	private EntityManager entityManager;

	public UserDaoImpl() {
		this.entityManager = JPAConfig.getEntityManager();
	}

	@Override
	public Users findByUserName(String username) {
		String jpql = "SELECT u FROM Users u WHERE u.username = :username";
		try {
			TypedQuery<Users> query = entityManager.createQuery(jpql, Users.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Không tìm thấy user hoặc lỗi
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
		String jpql = "SELECT u FROM Users u WHERE u.email = :email";
		try {
			TypedQuery<Users> query = entityManager.createQuery(jpql, Users.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Không tìm thấy user hoặc lỗi
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
				entityManager.merge(existingUser); // Cập nhật user
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
	public boolean isResetTokenValid(String token) {
		String jpql = "SELECT u.expiry FROM Users u WHERE u.token = :token";
		try {
			TypedQuery<Timestamp> query = entityManager.createQuery(jpql, Timestamp.class);
			query.setParameter("token", token);
			Timestamp expiryDate = query.getSingleResult();
			Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());

			System.out.println("Thời gian hiện tại: " + currentTime);
			System.out.println("Thời gian hết hạn: " + expiryDate);

			return expiryDate != null && expiryDate.after(currentTime);
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Không tìm thấy token hoặc lỗi
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
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();

		// Thêm user mới
		Users user = Users.builder().username("johndoe").password("password123").email("john.doe@example.com").build();
		userDao.insert(user);

		// Tìm user theo username
		Users foundUser = userDao.findByUserName("johndoe");
		System.out.println(foundUser);

		// Cập nhật mật khẩu qua email
		foundUser.setPassword("newpassword");
		userDao.updatePasswordByEmail(foundUser);

		// // Cập nhật token reset mật khẩu
		// userDao.updateResetToken("john.doe@example.com", "reset-token-123",
		// Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
		//
		// // Kiểm tra token reset hợp lệ
		// boolean isValid = userDao.isResetTokenValid("reset-token-123");
		// System.out.println("Token hợp lệ: " + isValid);
		//
		// // Đổi mật khẩu bằng token
		// userDao.updatePasswordByToken("reset-token-123", "new-secure-password");
	}

}
