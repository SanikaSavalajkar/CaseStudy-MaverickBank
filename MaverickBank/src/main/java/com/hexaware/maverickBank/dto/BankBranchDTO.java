package com.hexaware.maverickBank.dto;

public class BankBranchDTO {

    private Long branchId;
    private String name;
    private String address;
    private String ifscPrefix;

    public BankBranchDTO() {
    }

    public BankBranchDTO(Long branchId, String name, String address, String ifscPrefix) {
        this.branchId = branchId;
        this.name = name;
        this.address = address;
        this.ifscPrefix = ifscPrefix;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

