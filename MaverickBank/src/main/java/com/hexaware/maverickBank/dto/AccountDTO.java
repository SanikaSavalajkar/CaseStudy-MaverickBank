package com.hexaware.maverickBank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDTO {

    private Long accountId;
    private Long customerId;
    private Long branchId;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private LocalDateTime dateOpened;
    private String ifscCode;

    public AccountDTO() {
    }

    public AccountDTO(Long accountId, Long customerId, Long branchId, String accountNumber, String accountType, BigDecimal balance, LocalDateTime dateOpened, String ifscCode) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.branchId = branchId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.dateOpened = dateOpened;
        this.ifscCode = ifscCode;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public LocalDateTime getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDateTime dateOpened) {
        this.dateOpened = dateOpened;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}
