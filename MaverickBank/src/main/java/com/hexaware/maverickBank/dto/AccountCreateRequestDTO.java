package com.hexaware.maverickBank.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AccountCreateRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Branch ID is required")
    private Long branchId;

   

    @NotBlank(message = "Account type cannot be blank")
    private String accountType;

    @Positive(message = "Initial balance must be positive")
    @NotNull(message = "Initial balance is required")
    private BigDecimal balance;

    private String ifscCode;

    public AccountCreateRequestDTO() {
    }

    public AccountCreateRequestDTO(Long customerId, Long branchId, String accountType, BigDecimal balance, String ifscCode) {
        this.customerId = customerId;
        this.branchId = branchId;
 
        this.accountType = accountType;
        this.balance = balance;
        this.ifscCode = ifscCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}