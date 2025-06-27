package com.hexaware.maverickBank.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.maverickBank.dto.BankEmployeeCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankEmployeeDTO;
import com.hexaware.maverickBank.dto.BankEmployeeUpdateRequestDTO;
import com.hexaware.maverickBank.dto.UserDTO;
import com.hexaware.maverickBank.dto.UserRegistrationRequestDTO;
import com.hexaware.maverickBank.dto.UserUpdateRequestDTO;
import com.hexaware.maverickBank.service.interfaces.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // User Management Endpoints

    @PostMapping("/createuser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        log.info("Received request to create user: {}", userRegistrationRequestDTO.getUsername());
        UserDTO createdUser = adminService.createUser(userRegistrationRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/getuserbyid/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        log.info("Received request to get user by ID: {}", userId);
        UserDTO user = adminService.getUserById(userId);
        return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Received request to get all users");
        List<UserDTO> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/updateuser/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        log.info("Received request to update user with ID: {} and data: {}", userId, userUpdateRequestDTO);
        UserDTO updatedUser = adminService.updateUser(userId, userUpdateRequestDTO);
        return (updatedUser != null) ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("Received request to delete user with ID: {}", userId);
        adminService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Bank Employee Management Endpoints

    @PostMapping("/createbankemployees")
    public ResponseEntity<BankEmployeeDTO> createBankEmployee(@RequestBody BankEmployeeCreateRequestDTO bankEmployeeCreateRequestDTO) {
        log.info("Received request to create bank employee with user ID: {}", bankEmployeeCreateRequestDTO.getUserId());
        BankEmployeeDTO createdEmployee = adminService.createBankEmployee(bankEmployeeCreateRequestDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getbankemployeesbyid/{employeeId}")
    public ResponseEntity<BankEmployeeDTO> getBankEmployeeById(@PathVariable Long employeeId) {
        log.info("Received request to get bank employee by ID: {}", employeeId);
        BankEmployeeDTO employee = adminService.getBankEmployeeById(employeeId);
        return (employee != null) ? new ResponseEntity<>(employee, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getallbankemployees")
    public ResponseEntity<List<BankEmployeeDTO>> getAllBankEmployees() {
        log.info("Received request to get all bank employees");
        List<BankEmployeeDTO> employees = adminService.getAllBankEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/updatebankemployees/{employeeId}")
    public ResponseEntity<BankEmployeeDTO> updateBankEmployee(@PathVariable Long employeeId, @RequestBody BankEmployeeUpdateRequestDTO bankEmployeeUpdateRequestDTO) {
        log.info("Received request to update bank employee with ID: {} and data: {}", employeeId, bankEmployeeUpdateRequestDTO);
        BankEmployeeDTO updatedEmployee = adminService.updateBankEmployee(employeeId, bankEmployeeUpdateRequestDTO);
        return (updatedEmployee != null) ? new ResponseEntity<>(updatedEmployee, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletebankemployees/{employeeId}")
    public ResponseEntity<Void> deleteBankEmployee(@PathVariable Long employeeId) {
        log.info("Received request to delete bank employee with ID: {}", employeeId);
        adminService.deleteBankEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}