package com.hexaware.maverickBank.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.maverickBank.dto.BankEmployeeCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankEmployeeDTO;
import com.hexaware.maverickBank.dto.BankEmployeeUpdateRequestDTO;
import com.hexaware.maverickBank.service.interfaces.BankEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class BankEmployeeController {

    @Autowired
    private BankEmployeeService bankEmployeeService;

    @PostMapping("/createBankEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankEmployeeDTO> createBankEmployee(@Valid @RequestBody BankEmployeeCreateRequestDTO bankEmployeeCreateRequestDTO) {
        BankEmployeeDTO createdBankEmployee = bankEmployeeService.createBankEmployee(bankEmployeeCreateRequestDTO);
        return new ResponseEntity<>(createdBankEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getBankEmployeeById/{employeeId}")
    public ResponseEntity<BankEmployeeDTO> getBankEmployeeById(@PathVariable Long employeeId) {
        try {
            BankEmployeeDTO bankEmployeeDTO = bankEmployeeService.getBankEmployeeById(employeeId);
            return new ResponseEntity<>(bankEmployeeDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllBankEmployees")
    public ResponseEntity<List<BankEmployeeDTO>> getAllBankEmployees() {
        List<BankEmployeeDTO> bankEmployeeDTOList = bankEmployeeService.getAllBankEmployees();
        return new ResponseEntity<>(bankEmployeeDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateBankEmployee/{employeeId}")
    public ResponseEntity<BankEmployeeDTO> updateBankEmployee(@PathVariable Long employeeId, @Valid @RequestBody BankEmployeeUpdateRequestDTO bankEmployeeUpdateRequestDTO) {
        try {
            BankEmployeeDTO updatedBankEmployee = bankEmployeeService.updateBankEmployee(employeeId, bankEmployeeUpdateRequestDTO);
            return new ResponseEntity<>(updatedBankEmployee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteBankEmployee/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBankEmployee(@PathVariable Long employeeId) {
        try {
            bankEmployeeService.deleteBankEmployee(employeeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBankEmployeeByUserId/{userId}")
    public ResponseEntity<BankEmployeeDTO> getBankEmployeeByUserId(@PathVariable Long userId) {
        try {
            BankEmployeeDTO bankEmployeeDTO = bankEmployeeService.getBankEmployeeByUserId(userId);
            return new ResponseEntity<>(bankEmployeeDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
