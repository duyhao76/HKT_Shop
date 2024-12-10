package vn.HKT.dtos;

import java.time.LocalDate;
import vn.HKT.entities.Users;

public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String roleName; // Tên của Role
    private LocalDate createdDate;
    private LocalDate lastLogin;

    public UserDTO(Users user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate() != null ? user.getCreatedDate() : LocalDate.now();
        this.lastLogin = user.getLastLogin() != null ? user.getLastLogin() : LocalDate.now();
        this.roleName = (user.getRole() != null && user.getRole().getRoleName() != null)
                        ? user.getRole().getRoleName()
                        : "USER";
    }

    // Getters và Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", email=" + email + ", roleName=" + roleName
				+ ", createdDate=" + createdDate + ", lastLogin=" + lastLogin + "]";
	}
    
}
