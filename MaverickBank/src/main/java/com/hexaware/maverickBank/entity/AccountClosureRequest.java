package com.hexaware.maverickBank.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_closure_requests")
public class AccountClosureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long closureRequestId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    private LocalDateTime requestDate;

    private String reason;

    private String status; // e.g., Pending, Approved, Rejected

    public Long getClosureRequestId() {
        return closureRequestId;
    }

    public void setClosureRequestId(Long closureRequestId) {
        this.closureRequestId = closureRequestId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountClosureRequest that = (AccountClosureRequest) o;
        return Objects.equals(closureRequestId, that.closureRequestId) && Objects.equals(customer, that.customer) && Objects.equals(account, that.account) && Objects.equals(requestDate, that.requestDate) && Objects.equals(reason, that.reason) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(closureRequestId, customer, account, requestDate, reason, status);
    }

    @Override
    public String toString() {
        return "AccountClosureRequest{" +
                "closureRequestId=" + closureRequestId +
                ", customer=" + (customer != null ? customer.getCustomerId() : null) +
                ", account=" + (account != null ? account.getAccountId() : null) +
                ", requestDate=" + requestDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}