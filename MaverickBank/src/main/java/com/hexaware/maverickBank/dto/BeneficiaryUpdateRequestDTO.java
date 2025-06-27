package com.hexaware.maverickBank.dto;

import jakarta.validation.constraints.NotBlank;

public class BeneficiaryUpdateRequestDTO {

    @NotBlank(message = "Beneficiary name cannot be blank")
    private String beneficiaryName;

    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    @NotBlank(message = "Bank name cannot be blank")
    private String bankName;

    @NotBlank(message = "Branch name cannot be blank")
    private String branchName;

    @NotBlank(message = "IFSC code cannot be blank")
    private String ifscCode;

    public BeneficiaryUpdateRequestDTO() {
    }

    public BeneficiaryUpdateRequestDTO(String beneficiaryName, String accountNumber, String bankName, String branchName, String ifscCode) {
        this.beneficiaryName = beneficiaryName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}