package vn.HKT.services;

import java.sql.SQLException;
import java.util.List;

import vn.HKT.entities.Users;

public interface IUserService {

	Users login(String email, String password);

	boolean register(String email, String fullName, String password);

	Users FindByEmail(String email);

	void sendResetToken(String email);

	void updatePasswordByToken(String newPassword, String resetToken) throws SQLException;

	boolean updatePasswordByEmail(String email, String newPassword);
	
	List<Users> findAllUsers();
	
	Users findUserById(String id);
	
	void editUserRoleById(String role, String id);
}
