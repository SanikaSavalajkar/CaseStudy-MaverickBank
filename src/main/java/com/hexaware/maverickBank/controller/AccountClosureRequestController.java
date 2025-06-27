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

import com.hexaware.maverickBank.dto.AccountClosureRequestCreateRequestDTO;
import com.hexaware.maverickBank.dto.AccountClosureRequestDTO;
import com.hexaware.maverickBank.service.interfaces.AccountClosureRequestService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/account-closure-requests")
public class AccountClosureRequestController {

    @Autowired
    private AccountClosureRequestService accountClosureRequestService;

    @PostMapping("/createaccountclosurerequest")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountClosureRequestDTO> createAccountClosureRequest(@Valid @RequestBody AccountClosureRequestCreateRequestDTO requestDTO) {
        log.info("Received request to create account closure request: {}", requestDTO);
        AccountClosureRequestDTO createdRequest = accountClosureRequestService.createAccountClosureRequest(requestDTO);
        log.info("Account closure request created with ID: {}", createdRequest.getClosureRequestId());
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping("/getaccountclosurerequestbyid/{closureRequestId}")
    public ResponseEntity<AccountClosureRequestDTO> getAccountClosureRequestById(@PathVariable Long closureRequestId) {
        log.info("Received request to get account closure request by ID: {}", closureRequestId);
        try {
            AccountClosureRequestDTO requestDTO = accountClosureRequestService.getAccountClosureRequestById(closureRequestId);
            log.info("Account closure request found with ID: {}", requestDTO.getClosureRequestId());
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.warn("Account closure request not found with ID: {}", closureRequestId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getallaccountclosurerequests")
    public ResponseEntity<List<AccountClosureRequestDTO>> getAllAccountClosureRequests() {
        log.info("Received request to get all account closure requests.");
        List<AccountClosureRequestDTO> requestDTOList = accountClosureRequestService.getAllAccountClosureRequests();
        log.info("Retrieved {} account closure requests.", requestDTOList.size());
        return new ResponseEntity<>(requestDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateaccountclosurerequest/{closureRequestId}")
    public ResponseEntity<AccountClosureRequestDTO> updateAccountClosureRequest(@PathVariable Long closureRequestId, @Valid @RequestBody AccountClosureRequestDTO requestDTO) {
        log.info("Received request to update account closure request with ID: {} and data: {}", closureRequestId, requestDTO);
        try {
            AccountClosureRequestDTO updatedRequest = accountClosureRequestService.updateAccountClosureRequest(closureRequestId, requestDTO);
            log.info("Account closure request with ID {} updated.", updatedRequest.getClosureRequestId());
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.warn("Account closure request not found with ID: {}", closureRequestId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error updating account closure request: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteaccountclosurerequest/{closureRequestId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAccountClosureRequest(@PathVariable Long closureRequestId) {
        log.info("Received request to delete account closure request with ID: {}", closureRequestId);
        try {
            accountClosureRequestService.deleteAccountClosureRequest(closureRequestId);
            log.info("Account closure request with ID {} deleted.", closureRequestId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            log.warn("Account closure request not found with ID: {}", closureRequestId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAccountClosureRequestsByCustomerId/{customerId}")
    public ResponseEntity<List<AccountClosureRequestDTO>> getAccountClosureRequestsByCustomerId(@PathVariable Long customerId) {
        log.info("Received request to get account closure requests by Customer ID: {}", customerId);
        try {
            List<AccountClosureRequestDTO> requestDTOList = accountClosureRequestService.getAccountClosureRequestsByCustomerId(customerId);
            log.info("Retrieved {} account closure requests for Customer ID: {}", requestDTOList.size(), customerId);
            return new ResponseEntity<>(requestDTOList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.warn("No account closure requests found for Customer ID: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAccountClosureRequestByAccountId/{accountId}")
    public ResponseEntity<AccountClosureRequestDTO> getAccountClosureRequestByAccountId(@PathVariable Long accountId) {
        log.info("Received request to get account closure request by Account ID: {}", accountId);
        try {
            AccountClosureRequestDTO requestDTO = accountClosureRequestService.getAccountClosureRequestByAccountId(accountId);
            log.info("Account closure request found for Account ID: {}", requestDTO.getAccountId());
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.warn("Account closure request not found for Account ID: {}", accountId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
