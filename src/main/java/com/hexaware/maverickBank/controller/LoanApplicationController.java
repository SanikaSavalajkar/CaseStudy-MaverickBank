package com.hexaware.maverickBank.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.maverickBank.dto.LoanApplicationCreateRequestDTO;
import com.hexaware.maverickBank.dto.LoanApplicationDTO;
import com.hexaware.maverickBank.dto.LoanApplicationUpdateRequestDTO;
import com.hexaware.maverickBank.service.interfaces.LoanApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/loan-applications")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/createLoanApplication")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoanApplicationDTO> createLoanApplication(@Valid @RequestBody LoanApplicationCreateRequestDTO loanApplicationCreateRequestDTO) {
        LoanApplicationDTO createdLoanApplication = loanApplicationService.createLoanApplication(loanApplicationCreateRequestDTO);
        return new ResponseEntity<>(createdLoanApplication, HttpStatus.CREATED);
    }

    @GetMapping("/getLoanApplicationById/{applicationId}")
    public ResponseEntity<LoanApplicationDTO> getLoanApplicationById(@PathVariable Long applicationId) {
        try {
            LoanApplicationDTO loanApplicationDTO = loanApplicationService.getLoanApplicationById(applicationId);
            return new ResponseEntity<>(loanApplicationDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllLoanApplications")
    public ResponseEntity<List<LoanApplicationDTO>> getAllLoanApplications() {
        List<LoanApplicationDTO> loanApplicationDTOList = loanApplicationService.getAllLoanApplications();
        return new ResponseEntity<>(loanApplicationDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateLoanApplication/{applicationId}")
    public ResponseEntity<LoanApplicationDTO> updateLoanApplication(@PathVariable Long applicationId, @Valid @RequestBody LoanApplicationUpdateRequestDTO loanApplicationUpdateRequestDTO) {
        try {
            LoanApplicationDTO updatedLoanApplication = loanApplicationService.updateLoanApplication(applicationId, loanApplicationUpdateRequestDTO);
            return new ResponseEntity<>(updatedLoanApplication, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteLoanApplication/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable Long applicationId) {
        try {
            loanApplicationService.deleteLoanApplication(applicationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getLoanApplicationsByCustomerId/{customerId}")
    public ResponseEntity<List<LoanApplicationDTO>> getLoanApplicationsByCustomerId(@PathVariable Long customerId) {
        try {
            List<LoanApplicationDTO> loanApplicationDTOList = loanApplicationService.getLoanApplicationsByCustomerId(customerId);
            return new ResponseEntity<>(loanApplicationDTOList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}