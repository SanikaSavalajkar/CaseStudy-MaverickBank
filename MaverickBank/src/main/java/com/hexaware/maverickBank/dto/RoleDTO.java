package com.hexaware.maverickBank.dto;

public class RoleDTO {

    private Long roleId;
    private String name;

    // Constructors
    public RoleDTO() {
    }

    public RoleDTO(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    // Getters and setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
