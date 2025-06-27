package com.hexaware.maverickBank.dto;

import java.math.BigDecimal;

public class AccountUpdateRequestDTO {

    private String accountType;
    private BigDecimal balance;
    private String ifscCode;

    public AccountUpdateRequestDTO() {
    }

    public AccountUpdateRequestDTO(String accountType, BigDecimal balance, String ifscCode) {
        this.accountType = accountType;
        this.balance = balance;
        this.ifscCode = ifscCode;
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