package com.hexaware.maverickBank.dto;

public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private Long roleId; // You might want to include the role ID

    // Constructors
    public UserDTO() {
    }

    public UserDTO(Long userId, String username, String email, Long roleId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roleId = roleId;
    }

    // Getters and setters
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
