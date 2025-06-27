package com.hexaware.maverickBank.dto;

import java.time.LocalDate;

public class CustomerUpdateRequestDTO {

    private String name;
    private String gender;
    private String contactNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String aadharNumber;
    private String panNumber;

    public CustomerUpdateRequestDTO() {
    }

    public CustomerUpdateRequestDTO(String name, String gender, String contactNumber, String address, LocalDate dateOfBirth, String aadharNumber, String panNumber) {
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.aadharNumber = aadharNumber;
        this.panNumber = panNumber;
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
}