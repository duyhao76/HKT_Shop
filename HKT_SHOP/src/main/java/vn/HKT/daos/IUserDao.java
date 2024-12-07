package vn.HKT.daos;

import java.sql.SQLException;
import java.sql.Timestamp;

import vn.HKT.entities.Users;

public interface IUserDao {

	Users findByUserName(String username);

	Users findByEmail(String email);

	void insert(Users user) throws SQLException, Exception;

	boolean isResetTokenValid(String token) throws SQLException;

	void updatePasswordByToken(String token, String hashedPassword) throws SQLException;

	void updateResetToken(String email, String token, Timestamp expiry) throws SQLException;

	void updatePasswordByEmail(Users user) throws SQLException;

}
