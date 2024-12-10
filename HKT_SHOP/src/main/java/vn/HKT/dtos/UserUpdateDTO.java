package vn.HKT.dtos;

import java.time.LocalDate;

public class UserUpdateDTO {
    private Long userId;
    private String username;
    private String email;
    private LocalDate lastLogin;

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

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

	@Override
	public String toString() {
		return "UserUpdateDTO [userId=" + userId + ", username=" + username + ", email=" + email + ", lastLogin="
				+ lastLogin + "]";
	}
    
    
}
