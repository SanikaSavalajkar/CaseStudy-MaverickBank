package com.hexaware.maverickBank.service.interfaces;

import java.util.List;

import com.hexaware.maverickBank.dto.BankEmployeeCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankEmployeeDTO;
import com.hexaware.maverickBank.dto.BankEmployeeUpdateRequestDTO;
import com.hexaware.maverickBank.dto.UserDTO;
import com.hexaware.maverickBank.dto.UserRegistrationRequestDTO;
import com.hexaware.maverickBank.dto.UserUpdateRequestDTO;

public interface AdminService {
	UserDTO createUser(UserRegistrationRequestDTO userCreateRequestDTO);
	UserDTO getUserById(Long userId);
	List<UserDTO> getAllUsers();
	UserDTO updateUser(Long userId, UserUpdateRequestDTO userUpdateRequestDTO);
	void deleteUser(Long userId);

    // Bank Employee Management
    BankEmployeeDTO createBankEmployee(BankEmployeeCreateRequestDTO bankEmployeeCreateRequestDTO);
    BankEmployeeDTO getBankEmployeeById(Long employeeId);
    List<BankEmployeeDTO> getAllBankEmployees();
    BankEmployeeDTO updateBankEmployee(Long employeeId, BankEmployeeUpdateRequestDTO bankEmployeeUpdateRequestDTO);
    void deleteBankEmployee(Long employeeId);
}