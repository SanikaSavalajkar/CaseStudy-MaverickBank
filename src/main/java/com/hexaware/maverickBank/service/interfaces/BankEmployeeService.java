package com.hexaware.maverickBank.service.interfaces;

import java.util.List;

import com.hexaware.maverickBank.dto.BankEmployeeCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankEmployeeDTO;
import com.hexaware.maverickBank.dto.BankEmployeeUpdateRequestDTO;

public interface BankEmployeeService {
    BankEmployeeDTO createBankEmployee(BankEmployeeCreateRequestDTO bankEmployeeCreateRequestDTO);
    BankEmployeeDTO getBankEmployeeById(Long employeeId);
    List<BankEmployeeDTO> getAllBankEmployees();
    BankEmployeeDTO updateBankEmployee(Long employeeId, BankEmployeeUpdateRequestDTO bankEmployeeUpdateRequestDTO);
    void deleteBankEmployee(Long employeeId);
    BankEmployeeDTO getBankEmployeeByUserId(Long userId);
   
}