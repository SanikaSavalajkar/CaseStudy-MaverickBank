package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the BankEmployeeService interface and handles
 *               business logic related to bank employees, including:
 * 
 *               - Creating bank employees linked to users and bank branches with validation
 *               - Retrieving bank employees by employee ID or user ID
 *               - Listing all bank employees
 *               - Updating bank employee details with validation on branch existence
 *               - Deleting bank employees by ID
 * 
 *               It ensures data integrity by validating employee details and verifying
 *               existence of associated users and bank branches before performing
 *               operations. It also converts between entity and DTO representations.
 * -----------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.BankEmployeeCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankEmployeeDTO;
import com.hexaware.maverickBank.dto.BankEmployeeUpdateRequestDTO;
import com.hexaware.maverickBank.entity.BankBranch;
import com.hexaware.maverickBank.entity.BankEmployee;
import com.hexaware.maverickBank.entity.User;
import com.hexaware.maverickBank.repository.IBankEmployeeRepository;
import com.hexaware.maverickBank.service.interfaces.BankBranchService;
import com.hexaware.maverickBank.service.interfaces.BankEmployeeService;
import com.hexaware.maverickBank.service.interfaces.UserService;

import jakarta.validation.ValidationException;

@Service
public class BankEmployeeServiceImpl implements BankEmployeeService {

    @Autowired
    private IBankEmployeeRepository bankEmployeeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BankBranchService bankBranchService;

    private void validateBankEmployee(BankEmployee employee) {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new ValidationException("Employee name cannot be empty");
        }
        if (employee.getContactNumber() == null || employee.getContactNumber().isEmpty()) {
            throw new ValidationException("Employee contact number cannot be empty");
        }
        if (employee.getBranch() == null || employee.getBranch().getBranchId() == null) {
            throw new ValidationException("Branch ID is required");
        }
        try {
            bankBranchService.getBankBranchById(employee.getBranch().getBranchId());
        } catch (NoSuchElementException e) {
            throw new ValidationException("Branch with ID " + employee.getBranch().getBranchId() + " not found.");
        }
    }

    @Override
    public BankEmployeeDTO createBankEmployee(BankEmployeeCreateRequestDTO requestDTO) {
        if (requestDTO.getUserId() == null || userService.getUserById(requestDTO.getUserId()) == null) {
            throw new ValidationException("User ID is required and must exist");
        }
        BankEmployee employee = convertCreateRequestDTOtoEntity(requestDTO);
        validateBankEmployee(employee);
        BankEmployee savedEmployee = bankEmployeeRepository.save(employee);
        return convertEntityToDTO(savedEmployee);
    }

    @Override
    public BankEmployeeDTO getBankEmployeeById(Long employeeId) {
        BankEmployee employee = bankEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Bank employee not found with ID: " + employeeId));
        return convertEntityToDTO(employee);
    }

    @Override
    public List<BankEmployeeDTO> getAllBankEmployees() {
        List<BankEmployee> employees = bankEmployeeRepository.findAll();
        List<BankEmployeeDTO> dtos = new ArrayList<>();
        for (BankEmployee employee : employees) {
            dtos.add(convertEntityToDTO(employee));
        }
        return dtos;
    }

    @Override
    public BankEmployeeDTO updateBankEmployee(Long employeeId, BankEmployeeUpdateRequestDTO requestDTO) {
        BankEmployee existingEmployee = bankEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Bank employee not found with ID: " + employeeId));

        if (requestDTO.getName() != null) {
            existingEmployee.setName(requestDTO.getName());
        }
        if (requestDTO.getContactNumber() != null) {
            existingEmployee.setContactNumber(requestDTO.getContactNumber());
        }
        if (requestDTO.getBranchId() != null) {
            BankBranch branch = new BankBranch();
            branch.setBranchId(requestDTO.getBranchId());
            existingEmployee.setBranch(branch);
            try {
                bankBranchService.getBankBranchById(requestDTO.getBranchId());
            } catch (NoSuchElementException e) {
                throw new ValidationException("Branch with ID " + requestDTO.getBranchId() + " not found.");
            }
        }

        validateBankEmployee(existingEmployee);
        BankEmployee updatedEmployee = bankEmployeeRepository.save(existingEmployee);
        return convertEntityToDTO(updatedEmployee);
    }

    @Override
    public void deleteBankEmployee(Long employeeId) {
        if (!bankEmployeeRepository.existsById(employeeId)) {
            throw new NoSuchElementException("Bank employee not found with ID: " + employeeId);
        }
        bankEmployeeRepository.deleteById(employeeId);
    }

    @Override
    public BankEmployeeDTO getBankEmployeeByUserId(Long userId) {
        BankEmployee employee = bankEmployeeRepository.findByUser_UserId(userId);
        if (employee == null) {
            throw new NoSuchElementException("Bank employee not found for User ID: " + userId);
        }
        return convertEntityToDTO(employee);
    }

    private BankEmployeeDTO convertEntityToDTO(BankEmployee employee) {
        BankEmployeeDTO dto = new BankEmployeeDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        if (employee.getUserId() != null) {
            dto.setUserId(employee.getUserId().getUserId());
        }
        dto.setName(employee.getName());
        dto.setContactNumber(employee.getContactNumber());
        if (employee.getBranch() != null) {
            dto.setBranchId(employee.getBranch().getBranchId());
        }
        return dto;
    }

    private BankEmployee convertCreateRequestDTOtoEntity(BankEmployeeCreateRequestDTO dto) {
        BankEmployee employee = new BankEmployee();
        User user = new User();
        user.setUserId(dto.getUserId());
        employee.setUserId(user);
        employee.setName(dto.getName());
        employee.setContactNumber(dto.getContactNumber());
        BankBranch branch = new BankBranch();
        branch.setBranchId(dto.getBranchId());
        employee.setBranch(branch);
        return employee;
    }
}