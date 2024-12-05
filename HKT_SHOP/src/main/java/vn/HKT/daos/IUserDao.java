package vn.HKT.daos;

import java.sql.SQLException;
import vn.HKT.entities.Users;

public interface IUserDao {

	Users findByUserName(String username);

	Users findByEmail(String email);

	void insert(Users user) throws SQLException, Exception;
	
	void updatePasswordByEmail(Users user) throws SQLException;

}
