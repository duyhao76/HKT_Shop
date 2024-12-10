package vn.HKT.controllers.api;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HKT.dtos.UserDTO;
import vn.HKT.dtos.UserUpdateDTO;
import vn.HKT.entities.Users;
import vn.HKT.services.IUserService;
import vn.HKT.services.impl.UserServiceImpl;
import vn.HKT.utils.HttpUtils;

@WebServlet(urlPatterns = { "/api-admin-users" })
public class UserApiController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IUserService userService = new UserServiceImpl();

	private final ObjectMapper mapper;

	public UserApiController() {
	    // Đăng ký JavaTimeModule để hỗ trợ LocalDate và LocalDateTime
	    this.mapper = new ObjectMapper();
	    this.mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
	    this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Định dạng ngày/thời gian thành chuỗi ISO
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("application/json; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        List<UserDTO> users = userService.getAllUsers();

	        // Debug để in DTO
	        System.out.println("UserDTOs: " + users);

	        ObjectMapper mapper = new ObjectMapper();
	        mapper.findAndRegisterModules(); // Hỗ trợ Java 8 date/time
	        mapper.writeValue(response.getOutputStream(), users);
	        response.getOutputStream().flush();
	    } catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        mapper.writeValue(response.getOutputStream(),
	                "{\"error\": \"Internal server error occurred. Details: " + e.getMessage() + "\"}");
	        response.getOutputStream().flush();
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        // Parse request JSON thành Users entity
	        Users requestUser = HttpUtils.of(request.getReader()).toEntity(Users.class);
	        
	        if (requestUser == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            mapper.writeValue(response.getOutputStream(), "{\"error\": \"Invalid request payload.\"}");
	            return;
	        }

	        // Gọi service để thêm mới user
	        Users insertedUser = userService.insert(requestUser);

	        // Convert entity thành DTO để trả về
	        UserDTO userDTO = new UserDTO(insertedUser);

	        // Phản hồi thành công với DTO
	        response.setStatus(HttpServletResponse.SC_CREATED);
	        mapper.writeValue(response.getOutputStream(), userDTO);

	    } catch (IllegalArgumentException e) {
	        // Trả về lỗi khi email hoặc username đã tồn tại
	        response.setStatus(HttpServletResponse.SC_CONFLICT);
	        mapper.writeValue(response.getOutputStream(),
	                "{\"error\": \"" + e.getMessage() + "\"}");

	    } catch (Exception e) {
	        // Xử lý lỗi hệ thống
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        mapper.writeValue(response.getOutputStream(),
	                "{\"error\": \"Internal server error occurred.\"}");
	    }
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        UserUpdateDTO requestUserDTO = HttpUtils.of(request.getReader()).toEntity(UserUpdateDTO.class);
	        if (requestUserDTO.getUserId() == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            mapper.writeValue(response.getOutputStream(), "{\"error\": \"User ID is required.\"}");
	            return;
	        }

	        Users existingUser = userService.findById(requestUserDTO.getUserId());
	        if (existingUser == null) {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            mapper.writeValue(response.getOutputStream(), "{\"error\": \"User not found.\"}");
	            return;
	        }

	        // Cập nhật người dùng
	        existingUser.setUsername(requestUserDTO.getUsername());
	        existingUser.setEmail(requestUserDTO.getEmail());
	        existingUser.setLastLogin(requestUserDTO.getLastLogin());

	        Users updatedUser = userService.edit(existingUser);
	        UserDTO responseDTO = new UserDTO(updatedUser);
	        mapper.writeValue(response.getOutputStream(), responseDTO);

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        mapper.writeValue(response.getOutputStream(), "{\"error\": \"Internal server error: " + e.getMessage() + "\"}");
	    }
	}

}
