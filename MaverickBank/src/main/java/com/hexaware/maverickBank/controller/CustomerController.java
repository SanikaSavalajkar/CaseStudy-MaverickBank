package com.hexaware.maverickBank.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.maverickBank.dto.CustomerCreateRequestDTO;
import com.hexaware.maverickBank.dto.CustomerDTO;
import com.hexaware.maverickBank.dto.CustomerUpdateRequestDTO;
import com.hexaware.maverickBank.service.interfaces.CustomerServcie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerServcie customerService;

    @PostMapping("/createCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerCreateRequestDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        try {
            CustomerDTO customerDTO = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerUpdateRequestDTO);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCustomerByUserId/{userId}")
    public ResponseEntity<CustomerDTO> getCustomerByUserId(@PathVariable Long userId) {
        try {
            CustomerDTO customerDTO = customerService.getCustomerByUserId(userId);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
