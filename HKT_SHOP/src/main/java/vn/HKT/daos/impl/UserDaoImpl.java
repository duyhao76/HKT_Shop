package vn.HKT.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.HKT.configs.JPAConfig;
import vn.HKT.daos.IUserDao;
import vn.HKT.entities.Users;


public class UserDaoImpl implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	EntityManager enma = JPAConfig.getEntityManager();
	EntityTransaction trans = enma.getTransaction();

	@Override
	public Users findByUserName(String username) {
		String sql = "select * from USERS where username = ?";

		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserId(Long.parseLong(rs.getString("userId")));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.getRole();
				user.getCreatedDate();
				user.getLastLogin();
				user.getToken();
				user.getExpiry();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Users user) throws SQLException, Exception {
		String sql = "INSERT INTO USERS (username, email, password)" + "VALUES (?, ?, ?)";

		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Users findByEmail(String email) {
		String sql = "select * from USERS where email = ?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserId(Long.parseLong(rs.getString("userId")));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.getRole();
				user.getCreatedDate();
				user.getLastLogin();
				user.getToken();
				user.getExpiry();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void updatePasswordByEmail(Users user) throws SQLException {
	    String sql = "UPDATE USERS SET password = ? WHERE email = ?";
	    try (
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, user.getPassword());
	        ps.setString(2, user.getEmail());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}


	public static void main(String[] args) {
//		UserDaoImpl userDao = new UserDaoImpl(); // Tạo đối tượng DAO để gọi hàm kiểm tra
//
//		//Thử nghiệm với các token khác nhau (đảm bảo token đã tồn tại trong databaseđể kiểm tra)
//		String testToken1 = "54f006e1-ffb5-441c-9dad-14025547153b"; // Token có thể tồn tại và hợp lệ Kiểm tra token hợp lệ
//		boolean isValid1 = userDao.isResetTokenValid(testToken1);
//		System.out.println("Token " + testToken1 + " hợp lệ: " + isValid1);
//		System.out.println("Thời gian hiện tại (ứng dụng): " + new Timestamp(System.currentTimeMillis()));
//
//		new Users();
//		//userDao.findByResetPasswordToken(testToken1);
	}

}