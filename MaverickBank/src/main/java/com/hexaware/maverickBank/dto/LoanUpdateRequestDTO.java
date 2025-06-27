package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class LoanUpdateRequestDTO {

    @Positive(message = "Principal amount must be positive")
    private BigDecimal principalAmount;

    @Positive(message = "Interest rate must be positive")
    private BigDecimal interestRate; // Changed to BigDecimal

    @Positive(message = "Tenure must be positive")
    private Integer tenureMonths;

    private BigDecimal amountPaid;

    private String status;

    // Constructors, getters, setters
    public LoanUpdateRequestDTO() {
    }

    public LoanUpdateRequestDTO(BigDecimal principalAmount, BigDecimal interestRate, Integer tenureMonths, BigDecimal amountPaid, String status) {
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
        this.amountPaid = amountPaid;
        this.status = status;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}