package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the LoanApplicationService interface and handles
 *               business logic related to loan application management, such as:
 * 
 *               - Creating new loan applications
 *               - Retrieving loan applications by ID
 *               - Listing all loan applications
 *               - Updating loan applications (partial updates allowed)
 *               - Deleting loan applications by ID
 *               - Fetching loan applications by customer ID
 * 
 *               It includes validation logic to ensure loan application data integrity
 *               and converts between DTOs and entity models.
 * -----------------------------------------------------------------------------
 */

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.LoanApplicationCreateRequestDTO;
import com.hexaware.maverickBank.dto.LoanApplicationDTO;
import com.hexaware.maverickBank.dto.LoanApplicationUpdateRequestDTO;
import com.hexaware.maverickBank.entity.Customer;
import com.hexaware.maverickBank.entity.Loan;
import com.hexaware.maverickBank.entity.LoanApplication;
import com.hexaware.maverickBank.repository.ILoanApplicationRepository;
import com.hexaware.maverickBank.service.interfaces.LoanApplicationService;

import jakarta.validation.ValidationException;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private ILoanApplicationRepository loanApplicationRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private LoanServiceImpl loanService;

    private void validateLoanApplication(LoanApplication loanApplication) {
        if (loanApplication.getRequestedAmount() == null || loanApplication.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Requested loan amount must be positive");
        }
        if (loanApplication.getPurpose() == null || loanApplication.getPurpose().isEmpty()) {
            throw new ValidationException("Purpose of loan cannot be empty");
        }
        
    }

    @Override
    public LoanApplicationDTO createLoanApplication(LoanApplicationCreateRequestDTO loanApplicationCreateRequestDTO) {
        LoanApplication loanApplication = convertCreateRequestDTOtoEntity(loanApplicationCreateRequestDTO);
        if (loanApplication.getCustomer() == null || loanApplication.getCustomer().getCustomerId() == null) {
            throw new ValidationException("Customer ID is required");
        }
        if (loanApplication.getLoan() == null || loanApplication.getLoan().getLoanId() == null) {
            throw new ValidationException("Loan ID is required");
        }
        customerService.getCustomerById(loanApplication.getCustomer().getCustomerId()); // Ensure customer exists
        loanService.getLoanById(loanApplication.getLoan().getLoanId()); // Ensure loan exists
        validateLoanApplication(loanApplication);
        loanApplication.setApplicationDate(LocalDateTime.now());
        loanApplication.setStatus("Pending"); // Default status
        LoanApplication savedApplication = loanApplicationRepository.save(loanApplication);
        return convertEntityToDTO(savedApplication);
    }

    @Override
    public LoanApplicationDTO getLoanApplicationById(Long applicationId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("Loan application not found with ID: " + applicationId));
        return convertEntityToDTO(loanApplication);
    }

    @Override
    public List<LoanApplicationDTO> getAllLoanApplications() {
        return loanApplicationRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanApplicationDTO updateLoanApplication(Long applicationId, LoanApplicationUpdateRequestDTO loanApplicationUpdateRequestDTO) {
        LoanApplication existingApplication = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new NoSuchElementException("Loan application not found with ID: " + applicationId));
        if (loanApplicationUpdateRequestDTO.getRequestedAmount() != null) {
            existingApplication.setRequestedAmount(loanApplicationUpdateRequestDTO.getRequestedAmount());
        }
        if (loanApplicationUpdateRequestDTO.getPurpose() != null) {
            existingApplication.setPurpose(loanApplicationUpdateRequestDTO.getPurpose());
        }
        if (loanApplicationUpdateRequestDTO.getStatus() != null) {
            existingApplication.setStatus(loanApplicationUpdateRequestDTO.getStatus());
        }
        validateLoanApplication(existingApplication);
        LoanApplication updatedApplication = loanApplicationRepository.save(existingApplication);
        return convertEntityToDTO(updatedApplication);
    }

    @Override
    public void deleteLoanApplication(Long applicationId) {
        if (!loanApplicationRepository.existsById(applicationId)) {
            throw new NoSuchElementException("Loan application not found with ID: " + applicationId);
        }
        loanApplicationRepository.deleteById(applicationId);
    }

    @Override
    public List<LoanApplicationDTO> getLoanApplicationsByCustomerId(Long customerId) {
        customerService.getCustomerById(customerId); // Ensure customer exists
        List<LoanApplication> applications = loanApplicationRepository.findByCustomer_CustomerId(customerId);
        if (applications.isEmpty()) {
            throw new NoSuchElementException("No loan applications found for Customer ID: " + customerId);
        }
        return applications.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private LoanApplicationDTO convertEntityToDTO(LoanApplication loanApplication) {
        LoanApplicationDTO dto = new LoanApplicationDTO();
        dto.setApplicationId(loanApplication.getApplicationId());
        if (loanApplication.getCustomer() != null) {
            dto.setCustomerId(loanApplication.getCustomer().getCustomerId());
        }
        if (loanApplication.getLoan() != null) {
            dto.setLoanId(loanApplication.getLoan().getLoanId());
        }
        dto.setRequestedAmount(loanApplication.getRequestedAmount());
        dto.setPurpose(loanApplication.getPurpose());
        dto.setApplicationDate(loanApplication.getApplicationDate());
        dto.setStatus(loanApplication.getStatus());
        return dto;
    }

    private LoanApplication convertCreateRequestDTOtoEntity(LoanApplicationCreateRequestDTO createRequestDTO) {
        LoanApplication loanApplication = new LoanApplication();
        Customer customer = new Customer();
        customer.setCustomerId(createRequestDTO.getCustomerId());
        loanApplication.setCustomer(customer);
        Loan loan = new Loan();
        loan.setLoanId(createRequestDTO.getLoanId());
        loanApplication.setLoan(loan);
        loanApplication.setRequestedAmount(createRequestDTO.getRequestedAmount());
        loanApplication.setPurpose(createRequestDTO.getPurpose());
        return loanApplication;
    }
}