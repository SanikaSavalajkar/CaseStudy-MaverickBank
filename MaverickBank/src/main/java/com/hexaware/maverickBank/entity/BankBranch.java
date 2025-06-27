package com.hexaware.maverickBank.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_branches")
public class BankBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String ifscPrefix;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    
    private List<BankEmployee> employees;
    
    public BankBranch() {
    }
    
    public BankBranch(Long branchId, String name, String address, String ifscPrefix) {
        this.branchId = branchId;
        this.name = name;
        this.address = address;
        this.ifscPrefix = ifscPrefix;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIfscPrefix() {
        return ifscPrefix;
    }

    public void setIfscPrefix(String ifscPrefix) {
        this.ifscPrefix = ifscPrefix;
    }

    @JsonManagedReference
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<BankEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<BankEmployee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankBranch that = (BankBranch) o;
        return Objects.equals(branchId, that.branchId) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(ifscPrefix, that.ifscPrefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, name, address, ifscPrefix);
    }

    @Override
    public String toString() {
        return "BankBranch{" +
                "branchId=" + branchId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", ifscPrefix='" + ifscPrefix + '\'' +
                '}';
    }
}
