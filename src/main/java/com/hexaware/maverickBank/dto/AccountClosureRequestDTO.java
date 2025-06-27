package com.hexaware.maverickBank.dto;

import java.time.LocalDateTime;

public class AccountClosureRequestDTO {

    private Long closureRequestId;
    private Long customerId;
    private Long accountId;
    private LocalDateTime requestDate;
    private String reason;
    private String status;

    public AccountClosureRequestDTO() {
    }

    public AccountClosureRequestDTO(Long closureRequestId, Long customerId, Long accountId, LocalDateTime requestDate, String reason, String status) {
        this.closureRequestId = closureRequestId;
        this.customerId = customerId;
        this.accountId = accountId;
        this.requestDate = requestDate;
        this.reason = reason;
        this.status = status;
    }

    public Long getClosureRequestId() {
        return closureRequestId;
    }

    public void setClosureRequestId(Long closureRequestId) {
        this.closureRequestId = closureRequestId;
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

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
