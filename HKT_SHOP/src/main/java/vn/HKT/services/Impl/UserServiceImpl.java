package vn.HKT.services.Impl;

import vn.HKT.daos.IUserDao;
import vn.HKT.daos.Impl.UserDaoImpl;
import vn.HKT.entities.Users;
import vn.HKT.services.IUserService;
import vn.HKT.utils.HashPasswordUtils;

public class UserServiceImpl implements IUserService{

	IUserDao userDao = new UserDaoImpl();
	
	@Override
	public Users login(String email, String password) {
		String hashedpass = HashPasswordUtils.hashPasswordWithSHA256(password);
		Users user = this.FindByEmail(email);
		if (user != null && hashedpass.trim().equals(user.getPassword().trim())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String email, String fullName, String password) {
		Users existingUser = userDao.findByEmail(email);
		if (existingUser != null) {
			return false;
		}
		
		// Kiểm tra nếu password là null, đặt mật khẩu trống hoặc mật khẩu tạm
	    if (password == null) {
	        password = "123456"; // hoặc sử dụng mật khẩu tạm thời khác
	    }
	    
		Users newUser = new Users();
		newUser.setEmail(email);
		newUser.setUsername(fullName);
		newUser.setPassword(HashPasswordUtils.hashPasswordWithSHA256(password));
		try {
			userDao.insert(newUser);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Users FindByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
