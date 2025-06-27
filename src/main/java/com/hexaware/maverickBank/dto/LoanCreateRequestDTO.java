package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class LoanCreateRequestDTO {

    @NotBlank(message = "Loan type cannot be blank")
    private String loanType;

    @Positive(message = "Principal amount must be positive")
    private BigDecimal principalAmount;

    @Positive(message = "Interest rate must be positive")
    private BigDecimal interestRate; // Changed to BigDecimal

    @Positive(message = "Tenure must be positive")
    private Integer tenureMonths;

    public LoanCreateRequestDTO() {
    }

    public LoanCreateRequestDTO(String loanType, BigDecimal principalAmount, BigDecimal interestRate, Integer tenureMonths) {
        this.loanType = loanType;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
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
}