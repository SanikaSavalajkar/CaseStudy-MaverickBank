package com.hexaware.maverickBank.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(nullable = false)
	private String name;
    private String gender;
    private String contactNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String aadharNumber;
    private String panNumber;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanApplication> loanApplications;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountClosureRequest> accountClosureRequests;
    
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonManagedReference
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<LoanApplication> getLoanApplications() {
		return loanApplications;
	}
	public void setLoanApplications(List<LoanApplication> loanApplications) {
		this.loanApplications = loanApplications;
	}
	public List<AccountClosureRequest> getAccountClosureRequests() {
		return accountClosureRequests;
	}
	public void setAccountClosureRequests(List<AccountClosureRequest> accountClosureRequests) {
		this.accountClosureRequests = accountClosureRequests;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(aadharNumber, address, contactNumber, customerId, dateOfBirth, gender, name, panNumber,
				user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(aadharNumber, other.aadharNumber) && Objects.equals(address, other.address)
				&& Objects.equals(contactNumber, other.contactNumber) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(gender, other.gender)
				&& Objects.equals(name, other.name) && Objects.equals(panNumber, other.panNumber)
				&& Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", gender=" + gender + ", contactNumber="
				+ contactNumber + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", aadharNumber="
				+ aadharNumber + ", panNumber=" + panNumber + ", user=" + user + ", accounts=" + accounts
				+ ", loanApplications=" + loanApplications + ", accountClosureRequests=" + accountClosureRequests + "]";
	}
    
}
