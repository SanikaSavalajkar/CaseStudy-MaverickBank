package com.hexaware.maverickBank.service.interfaces;

import java.util.List;

import com.hexaware.maverickBank.dto.LoanApplicationCreateRequestDTO;
import com.hexaware.maverickBank.dto.LoanApplicationDTO;
import com.hexaware.maverickBank.dto.LoanApplicationUpdateRequestDTO;

public interface LoanApplicationService {
    LoanApplicationDTO createLoanApplication(LoanApplicationCreateRequestDTO loanApplicationCreateRequestDTO);
    LoanApplicationDTO getLoanApplicationById(Long applicationId);
    List<LoanApplicationDTO> getAllLoanApplications();
    LoanApplicationDTO updateLoanApplication(Long applicationId, LoanApplicationUpdateRequestDTO loanApplicationUpdateRequestDTO);
    void deleteLoanApplication(Long applicationId);
    List<LoanApplicationDTO> getLoanApplicationsByCustomerId(Long customerId);
}