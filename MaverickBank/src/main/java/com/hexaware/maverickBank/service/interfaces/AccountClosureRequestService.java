package com.hexaware.maverickBank.service.interfaces;

import java.util.List;

import com.hexaware.maverickBank.dto.AccountClosureRequestCreateRequestDTO;
import com.hexaware.maverickBank.dto.AccountClosureRequestDTO;

public interface AccountClosureRequestService {
    AccountClosureRequestDTO createAccountClosureRequest(AccountClosureRequestCreateRequestDTO requestDTO);
    AccountClosureRequestDTO getAccountClosureRequestById(Long closureRequestId);
    List<AccountClosureRequestDTO> getAllAccountClosureRequests();
    AccountClosureRequestDTO updateAccountClosureRequest(Long closureRequestId, AccountClosureRequestDTO requestDTO);
    void deleteAccountClosureRequest(Long closureRequestId);
    List<AccountClosureRequestDTO> getAccountClosureRequestsByCustomerId(Long customerId);
    AccountClosureRequestDTO getAccountClosureRequestByAccountId(Long accountId);
}