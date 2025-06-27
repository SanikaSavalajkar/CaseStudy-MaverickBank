package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BankBranchCreateRequestDTO {

    @NotBlank(message = "Branch name cannot be blank")
    private String name;

    @NotBlank(message = "Branch address cannot be blank")
    private String address;

    @NotBlank(message = "IFSC prefix cannot be blank")
    @Size(min = 4, max = 4, message = "IFSC prefix must be exactly 4 characters")
    private String ifscPrefix;

    public BankBranchCreateRequestDTO() {
    }

    public BankBranchCreateRequestDTO(String name, String address, String ifscPrefix) {
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