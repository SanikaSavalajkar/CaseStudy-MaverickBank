package com.hexaware.maverickBank.service.interfaces;

import java.util.List;

import com.hexaware.maverickBank.dto.BankBranchCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankBranchDTO;
import com.hexaware.maverickBank.dto.BankBranchUpdateRequestDTO;

public interface BankBranchService {
    BankBranchDTO createBankBranch(BankBranchCreateRequestDTO bankBranchCreateRequestDTO);
    BankBranchDTO getBankBranchById(Long branchId);
    List<BankBranchDTO> getAllBankBranches();
    BankBranchDTO updateBankBranch(Long branchId, BankBranchUpdateRequestDTO bankBranchUpdateRequestDTO);
    void deleteBankBranch(Long branchId);
    BankBranchDTO getBankBranchByName(String name);
    BankBranchDTO getBankBranchByIfscPrefix(String ifscPrefix);
}