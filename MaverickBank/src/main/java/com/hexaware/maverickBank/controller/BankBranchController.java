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

import com.hexaware.maverickBank.dto.BankBranchCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankBranchDTO;
import com.hexaware.maverickBank.dto.BankBranchUpdateRequestDTO;
import com.hexaware.maverickBank.service.interfaces.BankBranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/branches")
public class BankBranchController {

    @Autowired
    private BankBranchService bankBranchService;

    @PostMapping("/createBankBranch")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankBranchDTO> createBankBranch(@Valid @RequestBody BankBranchCreateRequestDTO bankBranchCreateRequestDTO) {
        BankBranchDTO createdBankBranch = bankBranchService.createBankBranch(bankBranchCreateRequestDTO);
        return new ResponseEntity<>(createdBankBranch, HttpStatus.CREATED);
    }

    @GetMapping("/getBankBranchById/{branchId}")
    public ResponseEntity<BankBranchDTO> getBankBranchById(@PathVariable Long branchId) {
        try {
            BankBranchDTO bankBranchDTO = bankBranchService.getBankBranchById(branchId);
            return new ResponseEntity<>(bankBranchDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllBankBranches")
    public ResponseEntity<List<BankBranchDTO>> getAllBankBranches() {
        List<BankBranchDTO> bankBranchDTOList = bankBranchService.getAllBankBranches();
        return new ResponseEntity<>(bankBranchDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateBankBranch/{branchId}")
    public ResponseEntity<BankBranchDTO> updateBankBranch(@PathVariable Long branchId, @Valid @RequestBody BankBranchUpdateRequestDTO bankBranchUpdateRequestDTO) {
        try {
            BankBranchDTO updatedBankBranch = bankBranchService.updateBankBranch(branchId, bankBranchUpdateRequestDTO);
            return new ResponseEntity<>(updatedBankBranch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteBankBranch/{branchId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBankBranch(@PathVariable Long branchId) {
        try {
            bankBranchService.deleteBankBranch(branchId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBankBranchByName/{name}")
    public ResponseEntity<BankBranchDTO> getBankBranchByName(@PathVariable String name) {
        try {
            BankBranchDTO bankBranchDTO = bankBranchService.getBankBranchByName(name);
            return new ResponseEntity<>(bankBranchDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBankBranchByIfscPrefix/{ifscPrefix}")
    public ResponseEntity<BankBranchDTO> getBankBranchByIfscPrefix(@PathVariable String ifscPrefix) {
        try {
            BankBranchDTO bankBranchDTO = bankBranchService.getBankBranchByIfscPrefix(ifscPrefix);
            return new ResponseEntity<>(bankBranchDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}