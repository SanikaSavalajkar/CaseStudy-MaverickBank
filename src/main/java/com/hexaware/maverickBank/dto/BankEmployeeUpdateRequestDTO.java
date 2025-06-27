package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.NotNull;

public class BankEmployeeUpdateRequestDTO {

    private String name;
    private String contactNumber;

    @NotNull(message = "Branch ID is required")
    private Long branchId;

    public BankEmployeeUpdateRequestDTO() {
    }

    public BankEmployeeUpdateRequestDTO(String name, String contactNumber, Long branchId) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.branchId = branchId;
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