package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the LoanService interface and handles 
 *               business logic related to loan management, such as:
 * 
 *               - Creating new loan records
 *               - Retrieving loans by ID
 *               - Listing all loans
 *               - Updating loan details (partial updates allowed)
 *               - Deleting loans by ID
 * 
 *               It includes validation logic to ensure loan data integrity
 *               and converts between DTOs and entity models.
 * -----------------------------------------------------------------------------
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.LoanCreateRequestDTO;
import com.hexaware.maverickBank.dto.LoanDTO;
import com.hexaware.maverickBank.dto.LoanUpdateRequestDTO;
import com.hexaware.maverickBank.entity.Loan;
import com.hexaware.maverickBank.repository.ILoanRepository;
import com.hexaware.maverickBank.service.interfaces.LoanService;

import jakarta.validation.ValidationException;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private ILoanRepository loanRepository;

    private void validateLoan(Loan loan) {
        if (loan.getLoanType() == null || loan.getLoanType().isEmpty()) {
            throw new ValidationException("Loan type cannot be empty");
        }
        if (loan.getPrincipalAmount() == null || loan.getPrincipalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Principal amount must be positive");
        }
        if (loan.getInterestRate() == null || loan.getInterestRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Interest rate cannot be negative");
        }
        if (loan.getTenureMonths() == null || loan.getTenureMonths() <= 0) {
            throw new ValidationException("Tenure months must be positive");
        }
    }

    @Override
    public LoanDTO createLoan(LoanCreateRequestDTO loanCreateRequestDTO) {
        Loan loan = convertCreateRequestDTOtoEntity(loanCreateRequestDTO);
        validateLoan(loan);
        Loan savedLoan = loanRepository.save(loan);
        return convertEntityToDTO(savedLoan);
    }

    @Override
    public LoanDTO getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with ID: " + loanId));
        return convertEntityToDTO(loan);
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO updateLoan(Long loanId, LoanUpdateRequestDTO loanUpdateRequestDTO) {
        Loan existingLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with ID: " + loanId));
        if (loanUpdateRequestDTO.getPrincipalAmount() != null) {
            existingLoan.setPrincipalAmount(loanUpdateRequestDTO.getPrincipalAmount());
        }
        if (loanUpdateRequestDTO.getInterestRate() != null) {
            existingLoan.setInterestRate(loanUpdateRequestDTO.getInterestRate());
        }
        if (loanUpdateRequestDTO.getTenureMonths() != null) {
            existingLoan.setTenureMonths(loanUpdateRequestDTO.getTenureMonths());
        }
       
       
        
        validateLoan(existingLoan);
        Loan updatedLoan = loanRepository.save(existingLoan);
        return convertEntityToDTO(updatedLoan);
    }

    @Override
    public void deleteLoan(Long loanId) {
        if (!loanRepository.existsById(loanId)) {
            throw new NoSuchElementException("Loan not found with ID: " + loanId);
        }
       
        loanRepository.deleteById(loanId);
    }

    private LoanDTO convertEntityToDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setLoanId(loan.getLoanId());
        dto.setLoanType(loan.getLoanType());
        dto.setPrincipalAmount(loan.getPrincipalAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setTenureMonths(loan.getTenureMonths());
        //dto.setAmountPaid(loan.getAmountPaid());
        //dto.setStatus(loan.getStatus());
        return dto;
    }

    private Loan convertCreateRequestDTOtoEntity(LoanCreateRequestDTO createRequestDTO) {
        Loan loan = new Loan();
        loan.setLoanType(createRequestDTO.getLoanType());
        loan.setPrincipalAmount(createRequestDTO.getPrincipalAmount());
        loan.setInterestRate(createRequestDTO.getInterestRate());
        loan.setTenureMonths(createRequestDTO.getTenureMonths());
        return loan;
    }
}