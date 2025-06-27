package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BankEmployeeCreateRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Employee name cannot be blank")
    private String name;

    private String contactNumber;

    @NotNull(message = "Branch ID is required")
    private Long branchId;

    private String employeeId; // Add this field

    public BankEmployeeCreateRequestDTO() {
    }

    public BankEmployeeCreateRequestDTO(Long userId, String name, String contactNumber, Long branchId) {
        this.userId = userId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.branchId = branchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
