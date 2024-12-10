package vn.HKT.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import vn.HKT.entities.Users;

public interface IUserDao {

	Users findByUsername(String username);

	Users findByEmail(String email);

	void insert(Users user) throws SQLException, Exception;

	boolean isResetTokenValid(String token) throws SQLException;

	void updatePasswordByToken(String token, String hashedPassword) throws SQLException;

	void updateResetToken(String email, String token, Timestamp expiry) throws SQLException;

	void updatePasswordByEmail(Users user) throws SQLException;

	List<Users> findAll();

	void update(Users requestUser);

	Users findById(Long userId);

}
