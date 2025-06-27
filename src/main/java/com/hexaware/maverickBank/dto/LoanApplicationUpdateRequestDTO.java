package com.hexaware.maverickBank.dto;

import java.math.BigDecimal;

public class LoanApplicationUpdateRequestDTO {

    private BigDecimal requestedAmount;
    private String purpose;
    private String status; // e.g., Approved, Rejected

    public LoanApplicationUpdateRequestDTO() {
    }

    public LoanApplicationUpdateRequestDTO(BigDecimal requestedAmount, String purpose, String status) {
        this.requestedAmount = requestedAmount;
        this.purpose = purpose;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}