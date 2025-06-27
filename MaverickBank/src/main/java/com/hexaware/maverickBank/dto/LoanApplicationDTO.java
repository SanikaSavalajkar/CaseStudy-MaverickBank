package com.hexaware.maverickBank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanApplicationDTO {

    private Long applicationId;
    private Long customerId;
    private Long loanId;
    private BigDecimal requestedAmount;
    private String purpose;
    private LocalDateTime applicationDate;
    private String status;

    // Constructors
    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(Long applicationId, Long customerId, Long loanId, BigDecimal requestedAmount, String purpose, LocalDateTime applicationDate, String status) {
        this.applicationId = applicationId;
        this.customerId = customerId;
        this.loanId = loanId;
        this.requestedAmount = requestedAmount;
        this.purpose = purpose;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    // Getters and setters
    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}