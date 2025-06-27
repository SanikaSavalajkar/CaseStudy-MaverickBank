package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.Size;

public class BankBranchUpdateRequestDTO {

    private String name;
    private String address;

    @Size(min = 4, max = 4, message = "IFSC prefix must be exactly 4 characters")
    private String ifscPrefix;

    public BankBranchUpdateRequestDTO() {
    }

    public BankBranchUpdateRequestDTO(String name, String address, String ifscPrefix) {
        this.name = name;
        this.address = address;
        this.ifscPrefix = ifscPrefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIfscPrefix() {
        return ifscPrefix;
    }

    public void setIfscPrefix(String ifscPrefix) {
        this.ifscPrefix = ifscPrefix;
    }
}