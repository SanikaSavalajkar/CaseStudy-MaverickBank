package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountClosureRequestCreateRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotBlank(message = "Reason for closure cannot be blank")
    private String reason;

    public AccountClosureRequestCreateRequestDTO() {
    }

    public AccountClosureRequestCreateRequestDTO(Long customerId, Long accountId, String reason) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.reason = reason;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}