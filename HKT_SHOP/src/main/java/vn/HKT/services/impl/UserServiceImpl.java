package vn.HKT.services.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

import vn.HKT.daos.IRoleDao;
import vn.HKT.daos.IUserDao;
import vn.HKT.daos.impl.RoleDaoImpl;
import vn.HKT.daos.impl.UserDaoImpl;
import vn.HKT.dtos.UserDTO;
import vn.HKT.entities.Roles;
import vn.HKT.entities.Users;
import vn.HKT.services.IUserService;
import vn.HKT.utils.Constant;
import vn.HKT.utils.HashPasswordUtils;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();
	IRoleDao roleDao = new RoleDaoImpl();

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
		// Kiểm tra nếu user đã tồn tại
		Users existingUser = userDao.findByEmail(email);
		if (existingUser != null) {
			return false; // Email đã được sử dụng
		}

		// Kiểm tra nếu password là null, đặt mật khẩu tạm thời
		if (password == null) {
			password = "123456"; // Mật khẩu mặc định
		}

		// Tạo user mới
		Users newUser = new Users();
		newUser.setEmail(email);
		newUser.setUsername(fullName);
		newUser.setPassword(HashPasswordUtils.hashPasswordWithSHA256(password));

		// Gán roleId dựa trên email
		Roles role;
		if (email.contains(Constant.STUDENT_EMAIL_DOMAIN)) {
			role = roleDao.findById(2L); // Role ID = 2 (admin, hoặc student role)
		} else {
			role = roleDao.findById(1L); // Role ID = 1 (user)
		}
		newUser.setRole(role);

		// Lưu user vào database
		try {
			userDao.insert(newUser);
			return true; // Đăng ký thành công
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Đăng ký thất bại
		}
	}

	@Override
	public Users FindByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public void sendResetToken(String email) {
		// Cài đặt múi giờ đồng bộ giữa database và java
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		System.out.println("Thời gian hiện tại (Việt Nam - ứng dụng): " + new Timestamp(System.currentTimeMillis()));

		Users user = userDao.findByEmail(email);
		if (user == null) {
			return; // Người dùng không tồn tại
		}

		// Tạo token và thời gian hết hạn
		String token = UUID.randomUUID().toString();
		Timestamp expiry = calculateExpiryDate(60); // 60 phút

		try {
			userDao.updateResetToken(email, token, expiry);
			// Gọi phương thức gửi email
			EmailService emailService = new EmailService();
			emailService.sendPasswordResetEmail(email, token);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // Đảm bảo sử dụng UTC
		calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Timestamp(calendar.getTimeInMillis());
	}

	@Override
	public void updatePasswordByToken(String newPassword, String resetToken) throws SQLException {
		System.out.println("Token nhận được trong service: " + resetToken);
		System.out.println("Pasword nhận được trong service: " + newPassword);

		if (resetToken != null) {
			// Kiểm tra tính hợp lệ của token
			boolean isValid = userDao.isResetTokenValid(resetToken);
			if (!isValid) {
				throw new IllegalArgumentException("Token không hợp lệ hoặc đã hết hạn.");
			}
		}

		// Mã hóa mật khẩu mới
		String hashedPassword = HashPasswordUtils.hashPasswordWithSHA256(newPassword);

		// Thực hiện cập nhật mật khẩu
		userDao.updatePasswordByToken(resetToken, hashedPassword);
	}

	@Override
	public boolean updatePasswordByEmail(String email, String newPassword) {
		Users user = userDao.findByEmail(email);
		if (user != null) {
			user.setPassword(HashPasswordUtils.hashPasswordWithSHA256(newPassword));
			try {
				userDao.updatePasswordByEmail(user); // Cập nhật mật khẩu vào cơ sở dữ liệu
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Users> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public Users findUserById(String id) {
		return userDao.findUserById(id);
	}

	@Override
	public void editUserRoleById(String role, String id) {
		userDao.editUserRoleById(role, id);
	}
	

    public List<UserDTO> getAllUsers() {
        List<Users> users = userDao.findAll();
        // Chuyển đổi từ Users sang UserDTO
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

	@Override
	public Users insert(Users requestUser) throws Exception {
	    // Kiểm tra email và username
	    Users existingUserByEmail = userDao.findByEmail(requestUser.getEmail());
	    Users existingUserByUsername = userDao.findByUsername(requestUser.getUsername());

	    if (existingUserByEmail != null) {
	        throw new IllegalArgumentException("Email đã được sử dụng.");
	    }
	    if (existingUserByUsername != null) {
	        throw new IllegalArgumentException("Username đã được sử dụng.");
	    }

	    // Gọi tầng DAO để thêm mới
	    userDao.insert(requestUser);
	    System.out.println("User đã được thêm thành công: " + requestUser);
	    return requestUser;
	}

	@Override
	public Users edit(Users requestUser) {
	    try {
	        // Tìm người dùng hiện tại theo userId
	        Users existingUser = userDao.findById(requestUser.getUserId());
	        if (existingUser == null) {
	            throw new IllegalArgumentException("Người dùng không tồn tại.");
	        }

	        // Không cho phép chỉnh sửa role
	        requestUser.setRole(existingUser.getRole());

	        // Gọi DAO để cập nhật
	        userDao.update(requestUser);

	        System.out.println("Cập nhật thành công người dùng: " + requestUser.getUsername());
	        return requestUser;

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi khi chỉnh sửa thông tin người dùng: " + e.getMessage());
	    }
	}

	
	public static void main(String[] args) {
	    IUserService userService = new UserServiceImpl();

	    try {
	        // Giả lập chỉnh sửa thông tin người dùng
	        Users updatedUser = Users.builder()
	                .email("john.doe@example.com") // Người dùng cần chỉnh sửa email này
	                .username("updatedUser")      // Cập nhật username
	                .password("newpassword123")   // Cập nhật mật khẩu
	                .build();

	        userService.edit(updatedUser);

	    } catch (Exception e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
	}

	@Override
	public Users findById(Long userId) {
	    try {
	        return userDao.findById(userId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi khi tìm người dùng với ID: " + userId);
	    }
	}


	
}
