package com.hexaware.maverickBank.dto;

public class BankEmployeeDTO {

    private Long employeeId;
    private Long userId;
    private String name;
    private String contactNumber;
    private Long branchId;

    public BankEmployeeDTO() {
    }

    public BankEmployeeDTO(Long employeeId, Long userId, String name, String contactNumber, Long branchId) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.branchId = branchId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
}
