package com.hexaware.maverickBank.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LoanApplicationCreateRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @Positive(message = "Requested amount must be positive")
    @NotNull(message = "Requested amount is required")
    private BigDecimal requestedAmount;

    private String purpose;

    public LoanApplicationCreateRequestDTO() {
    }

    public LoanApplicationCreateRequestDTO(Long customerId, Long loanId, BigDecimal requestedAmount, String purpose) {
        this.customerId = customerId;
        this.loanId = loanId;
        this.requestedAmount = requestedAmount;
        this.purpose = purpose;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}