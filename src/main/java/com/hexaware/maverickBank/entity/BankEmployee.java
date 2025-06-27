package com.hexaware.maverickBank.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_employees")
public class BankEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private BankBranch branch;

    public BankEmployee() {
    }

    public BankEmployee(User userId, String name, String contactNumber, BankBranch branch, User user) {
        this.user = user;
        this.name = name;
        this.contactNumber = contactNumber;
        this.branch = branch;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public BankBranch getBranch() {
        return branch;
    }

    public void setBranch(BankBranch branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankEmployee that = (BankEmployee) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(user, that.user) && Objects.equals(name, that.name) && Objects.equals(contactNumber, that.contactNumber) && Objects.equals(branch, that.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, user, name, contactNumber, branch);
    }

    @Override
    public String toString() {
        return "BankEmployee{" +
                "employeeId=" + employeeId +
                ", userId=" + user +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", branch=" + (branch != null ? branch.getBranchId() : null) +
                '}';
    }
}
