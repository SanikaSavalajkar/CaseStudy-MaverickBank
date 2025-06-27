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

import com.hexaware.maverickBank.dto.LoanCreateRequestDTO;
import com.hexaware.maverickBank.dto.LoanDTO;
import com.hexaware.maverickBank.dto.LoanUpdateRequestDTO;
import com.hexaware.maverickBank.service.interfaces.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/loans")

public class LoanController {

    @Autowired
    private LoanService loanService;
    @PostMapping("/createLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody LoanCreateRequestDTO loanCreateRequestDTO) {
        LoanDTO createdLoan = loanService.createLoan(loanCreateRequestDTO);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @GetMapping("/getLoanById/{loanId}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long loanId) {
        try {
            LoanDTO loanDTO = loanService.getLoanById(loanId);
            return new ResponseEntity<>(loanDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getAllLoans")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> loanDTOList = loanService.getAllLoans();
        return new ResponseEntity<>(loanDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateLoan/{loanId}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long loanId, @Valid @RequestBody LoanUpdateRequestDTO loanUpdateRequestDTO) {
        try {
            LoanDTO updatedLoan = loanService.updateLoan(loanId, loanUpdateRequestDTO);
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteLoan/{loanId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteLoan(@PathVariable Long loanId) {
        try {
            loanService.deleteLoan(loanId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}