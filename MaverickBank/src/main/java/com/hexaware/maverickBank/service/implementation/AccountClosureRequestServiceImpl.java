package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the AccountClosureRequestService interface
 *               and manages the business logic related to account closure requests,
 *               including:
 * 
 *               - Validating account closure requests to ensure presence of customer
 *                 and account IDs, valid reason for closure, and zero balance in account
 *               - Creating new account closure requests with default status "Pending"
 *               - Retrieving account closure requests by closure request ID, customer ID,
 *                 or account ID
 *               - Updating existing account closure requests with validation
 *               - Deleting account closure requests by ID
 *               - Converting between entity and DTO objects for data transfer and persistence
 * 
 *               The service performs validation checks, handles exceptions such as
 *               NoSuchElementException and ValidationException, and logs operations
 *               for traceability and debugging.
 * -----------------------------------------------------------------------------
 */


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.AccountClosureRequestCreateRequestDTO;
import com.hexaware.maverickBank.dto.AccountClosureRequestDTO;
import com.hexaware.maverickBank.entity.Account;
import com.hexaware.maverickBank.entity.AccountClosureRequest;
import com.hexaware.maverickBank.entity.Customer;
import com.hexaware.maverickBank.repository.IAccountClosureRequestRepository;
import com.hexaware.maverickBank.service.interfaces.AccountClosureRequestService;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountClosureRequestServiceImpl implements AccountClosureRequestService {

    @Autowired
    private IAccountClosureRequestRepository accountClosureRequestRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private AccountServiceImpl accountService;

    
    private void validateAccountClosureRequest(AccountClosureRequestDTO requestDTO) {
        log.info("Validating account closure request with DTO: {}", requestDTO);
        if (requestDTO.getCustomerId() == null) {
            log.warn("Customer ID is missing in account closure request.");
            throw new ValidationException("Customer ID is required");
        }
        if (requestDTO.getAccountId() == null) {
            log.warn("Account ID is missing in account closure request.");
            throw new ValidationException("Account ID is required");
        }
        if (requestDTO.getReason() == null || requestDTO.getReason().isEmpty()) {
            log.warn("Reason for closure is missing in account closure request.");
            throw new ValidationException("Reason for closure is required");
        }
        try {
            // Checks if balance is less than 2 before closure
            if (accountService.getAccountById(requestDTO.getAccountId()).getBalance().compareTo(BigDecimal.ZERO) > 1) {
                log.warn("Account {} has a non-zero balance. Cannot close.", requestDTO.getAccountId());
                throw new ValidationException("Account must have less than 1 to be closed");
            }
        } catch (NoSuchElementException e) {
            log.error("Account with ID {} not found during validation.", requestDTO.getAccountId(), e);
            throw e;
        }
        log.info("Account closure request validation successful for Account ID: {}", requestDTO.getAccountId());
    }

    @Override
    public AccountClosureRequestDTO createAccountClosureRequest(AccountClosureRequestCreateRequestDTO requestDTO) {
        log.info("Creating account closure request for Customer ID: {} and Account ID: {}", requestDTO.getCustomerId(), requestDTO.getAccountId());
        if (requestDTO.getCustomerId() == null) {
            log.warn("Customer ID is missing in create request.");
            throw new ValidationException("Customer ID is required");
        }
        if (requestDTO.getAccountId() == null) {
            log.warn("Account ID is missing in create request.");
            throw new ValidationException("Account ID is required");
        }
        try {
            customerService.getCustomerById(requestDTO.getCustomerId()); 
        } catch (NoSuchElementException e) {
            log.error("Customer with ID {} not found.", requestDTO.getCustomerId(), e);
            throw e;
        }
        try {
            accountService.getAccountById(requestDTO.getAccountId()); 
        } catch (NoSuchElementException e) {
            log.error("Account with ID {} not found.", requestDTO.getAccountId(), e);
            throw e;
        }
        // Convert to entity and validate
        AccountClosureRequest request = convertCreateRequestDTOtoEntity(requestDTO);
        validateAccountClosureRequest(convertEntityToDTO(request));
        request.setRequestDate(LocalDateTime.now());
        request.setStatus("Pending"); // Default status
        // Save to DB
        AccountClosureRequest savedRequest = accountClosureRequestRepository.save(request);
        AccountClosureRequestDTO responseDTO = convertEntityToDTO(savedRequest);
        log.info("Account closure request created successfully with ID: {}", responseDTO.getClosureRequestId());
        return responseDTO;
    }

    @Override
    public AccountClosureRequestDTO getAccountClosureRequestById(Long closureRequestId) {
        log.info("Fetching account closure request by ID: {}", closureRequestId);
        AccountClosureRequest request = accountClosureRequestRepository.findById(closureRequestId)
                .orElseThrow(() -> {
                    log.warn("Account closure request not found with ID: {}", closureRequestId);
                    return new NoSuchElementException("Account closure request not found with ID: " + closureRequestId);
                });
        AccountClosureRequestDTO responseDTO = convertEntityToDTO(request);
        log.info("Account closure request found with ID: {}", responseDTO.getClosureRequestId());
        return responseDTO;
    }

    @Override
    public List<AccountClosureRequestDTO> getAllAccountClosureRequests() {
        log.info("Fetching all account closure requests.");
        List<AccountClosureRequest> requests = accountClosureRequestRepository.findAll();
        List<AccountClosureRequestDTO> responseDTOs = requests.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        log.info("Fetched {} account closure requests.", responseDTOs.size());
        return responseDTOs;
    }

    @Override
    public AccountClosureRequestDTO updateAccountClosureRequest(Long closureRequestId, AccountClosureRequestDTO requestDTO) {
        log.info("Updating account closure request with ID: {} and DTO: {}", closureRequestId, requestDTO);
        AccountClosureRequest existingRequest = accountClosureRequestRepository.findById(closureRequestId)
                .orElseThrow(() -> {
                    log.warn("Account closure request not found with ID: {}", closureRequestId);
                    return new NoSuchElementException("Account closure request not found with ID: " + closureRequestId);
                });
        // Convert DTO to entity and update
        AccountClosureRequest request = convertDTOtoEntity(requestDTO);
        request.setClosureRequestId(closureRequestId);
        validateAccountClosureRequest(requestDTO);
        AccountClosureRequest updatedRequest = accountClosureRequestRepository.save(request);
        AccountClosureRequestDTO responseDTO = convertEntityToDTO(updatedRequest);
        log.info("Account closure request with ID {} updated successfully.", responseDTO.getClosureRequestId());
        return responseDTO;
    }

    @Override
    public void deleteAccountClosureRequest(Long closureRequestId) {
        log.info("Deleting account closure request with ID: {}", closureRequestId);
        if (!accountClosureRequestRepository.existsById(closureRequestId)) {
            log.warn("Account closure request not found with ID: {}", closureRequestId);
            throw new NoSuchElementException("Account closure request not found with ID: " + closureRequestId);
        }
        accountClosureRequestRepository.deleteById(closureRequestId);
        log.info("Account closure request with ID {} deleted successfully.", closureRequestId);
    }

    @Override
    public List<AccountClosureRequestDTO> getAccountClosureRequestsByCustomerId(Long customerId) {
        log.info("Fetching account closure requests for Customer ID: {}", customerId);
        try {
            customerService.getCustomerById(customerId); // Ensure customer exists
        } catch (NoSuchElementException e) {
            log.error("Customer with ID {} not found.", customerId, e);
            throw e;
        }
        List<AccountClosureRequest> requests = accountClosureRequestRepository.findByCustomer_CustomerId(customerId);
        if (requests.isEmpty()) {
            log.warn("No account closure requests found for Customer ID: {}", customerId);
            throw new NoSuchElementException("No account closure requests found for Customer ID: " + customerId);
        }
        List<AccountClosureRequestDTO> responseDTOs = requests.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        log.info("Fetched {} account closure requests for Customer ID: {}.", responseDTOs.size(), customerId);
        return responseDTOs;
    }

    @Override
    public AccountClosureRequestDTO getAccountClosureRequestByAccountId(Long accountId) {
        log.info("Fetching account closure request for Account ID: {}", accountId);
        try {
            accountService.getAccountById(accountId); // Ensure account exists
        } catch (NoSuchElementException e) {
            log.error("Account with ID {} not found.", accountId, e);
            throw e;
        }
        AccountClosureRequest request = accountClosureRequestRepository.findByAccount_AccountId(accountId);
        if (request == null) {
            log.warn("Account closure request not found for Account ID: {}", accountId);
            throw new NoSuchElementException("Account closure request not found for Account ID: " + accountId);
        }
        AccountClosureRequestDTO responseDTO = convertEntityToDTO(request);
        log.info("Account closure request found for Account ID: {}.", responseDTO.getAccountId());
        return responseDTO;
    }

    // Converts entity to DTO
    private AccountClosureRequestDTO convertEntityToDTO(AccountClosureRequest request) {
        AccountClosureRequestDTO dto = new AccountClosureRequestDTO();
        dto.setClosureRequestId(request.getClosureRequestId());
        if (request.getCustomer() != null) {
            dto.setCustomerId(request.getCustomer().getCustomerId());
        }
        if (request.getAccount() != null) {
            dto.setAccountId(request.getAccount().getAccountId());
        }
        dto.setRequestDate(request.getRequestDate());
        dto.setReason(request.getReason());
        dto.setStatus(request.getStatus());
        return dto;
    }

    // Converts create DTO to entity
    private AccountClosureRequest convertCreateRequestDTOtoEntity(AccountClosureRequestCreateRequestDTO createRequestDTO) {
        AccountClosureRequest request = new AccountClosureRequest();
        Customer customer = new Customer();
        customer.setCustomerId(createRequestDTO.getCustomerId());
        request.setCustomer(customer);
        Account account = new Account();
        account.setAccountId(createRequestDTO.getAccountId());
        request.setAccount(account);
        request.setReason(createRequestDTO.getReason());
        return request;
    }

    // Converts update DTO to entity
    private AccountClosureRequest convertDTOtoEntity(AccountClosureRequestDTO requestDTO) {
        AccountClosureRequest request = new AccountClosureRequest();
        request.setClosureRequestId(requestDTO.getClosureRequestId());
        Customer customer = new Customer();
        customer.setCustomerId(requestDTO.getCustomerId());
        request.setCustomer(customer);
        Account account = new Account();
        account.setAccountId(requestDTO.getAccountId());
        request.setAccount(account);
        request.setRequestDate(requestDTO.getRequestDate());
        request.setReason(requestDTO.getReason());
        request.setStatus(requestDTO.getStatus());
        return request;
    }
}