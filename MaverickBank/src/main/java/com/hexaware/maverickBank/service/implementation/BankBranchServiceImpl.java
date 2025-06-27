package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the BankBranchService interface and manages
 *               the business logic related to bank branches, including:
 * 
 *               - Creating bank branches with validation on name, address, and IFSC prefix
 *               - Retrieving bank branches by ID, name, or IFSC prefix
 *               - Listing all bank branches
 *               - Updating bank branch details with validation on unique name and IFSC prefix
 *               - Deleting bank branches by ID
 * 
 *               The class validates the IFSC prefix format (exactly 4 alphabetic characters)
 *               and prevents duplicate entries by checking existing names and IFSC prefixes.
 *               It also handles entity-to-DTO and DTO-to-entity conversions.
 * -----------------------------------------------------------------------------
 */

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.BankBranchCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankBranchDTO;
import com.hexaware.maverickBank.dto.BankBranchUpdateRequestDTO;
import com.hexaware.maverickBank.entity.BankBranch;
import com.hexaware.maverickBank.repository.IBankBranchRepository;
import com.hexaware.maverickBank.service.interfaces.BankBranchService;

import jakarta.validation.ValidationException;

@Service
public class BankBranchServiceImpl implements BankBranchService {

    @Autowired
    private IBankBranchRepository bankBranchRepository;

    private boolean isValidIfscPrefix(String ifscPrefix) {
        Pattern pattern = Pattern.compile("^[A-Za-z]{4}$");
        Matcher matcher = pattern.matcher(ifscPrefix);
        return matcher.matches();
    }

    @Override
    public BankBranchDTO createBankBranch(BankBranchCreateRequestDTO bankBranchCreateRequestDTO) {
        BankBranch branch = convertCreateRequestDTOtoEntity(bankBranchCreateRequestDTO);
        if (branch.getName() == null || branch.getName().isEmpty()) {
            throw new ValidationException("Branch name cannot be empty");
        }
        if (branch.getAddress() == null || branch.getAddress().isEmpty()) {
            throw new ValidationException("Branch address cannot be empty");
        }
        if (branch.getIfscPrefix() == null || branch.getIfscPrefix().isEmpty() || !isValidIfscPrefix(branch.getIfscPrefix())) {
            throw new ValidationException("Invalid IFSC prefix (must be 4 alphabetic characters)");
        }
        if (bankBranchRepository.findByName(branch.getName()) != null) {
            throw new ValidationException("Branch with name " + branch.getName() + " already exists");
        }
        if (bankBranchRepository.findByIfscPrefix(branch.getIfscPrefix()) != null) {
            throw new ValidationException("Branch with IFSC prefix " + branch.getIfscPrefix() + " already exists");
        }
        BankBranch savedBranch = bankBranchRepository.save(branch);
        return convertEntityToDTO(savedBranch);
    }

    @Override
    public BankBranchDTO getBankBranchById(Long branchId) {
        BankBranch branch = bankBranchRepository.findById(branchId)
                .orElseThrow(() -> new NoSuchElementException("Bank branch not found with ID: " + branchId));
        return convertEntityToDTO(branch);
    }

    @Override
    public List<BankBranchDTO> getAllBankBranches() {
        return bankBranchRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankBranchDTO updateBankBranch(Long branchId, BankBranchUpdateRequestDTO bankBranchUpdateRequestDTO) {
        BankBranch existingBranch = bankBranchRepository.findById(branchId)
                .orElseThrow(() -> new NoSuchElementException("Bank branch not found with ID: " + branchId));
        if (bankBranchUpdateRequestDTO.getName() != null && !bankBranchUpdateRequestDTO.getName().isEmpty() && !existingBranch.getName().equals(bankBranchUpdateRequestDTO.getName()) && bankBranchRepository.findByName(bankBranchUpdateRequestDTO.getName()) != null) {
            throw new ValidationException("Branch with name " + bankBranchUpdateRequestDTO.getName() + " already exists");
        }
        if (bankBranchUpdateRequestDTO.getIfscPrefix() != null && !bankBranchUpdateRequestDTO.getIfscPrefix().isEmpty() && !existingBranch.getIfscPrefix().equals(bankBranchUpdateRequestDTO.getIfscPrefix()) && bankBranchRepository.findByIfscPrefix(bankBranchUpdateRequestDTO.getIfscPrefix()) != null) {
            throw new ValidationException("Branch with IFSC prefix " + bankBranchUpdateRequestDTO.getIfscPrefix() + " already exists");
        }
        existingBranch.setBranchId(branchId);
        if (bankBranchUpdateRequestDTO.getName() != null) existingBranch.setName(bankBranchUpdateRequestDTO.getName());
        if (bankBranchUpdateRequestDTO.getAddress() != null) existingBranch.setAddress(bankBranchUpdateRequestDTO.getAddress());
        if (bankBranchUpdateRequestDTO.getIfscPrefix() != null) existingBranch.setIfscPrefix(bankBranchUpdateRequestDTO.getIfscPrefix());
        BankBranch updatedBranch = bankBranchRepository.save(existingBranch);
        return convertEntityToDTO(updatedBranch);
    }

    @Override
    public void deleteBankBranch(Long branchId) {
        if (!bankBranchRepository.existsById(branchId)) {
            throw new NoSuchElementException("Bank branch not found with ID: " + branchId);
        }
        
        bankBranchRepository.deleteById(branchId);
    }

    @Override
    public BankBranchDTO getBankBranchByName(String name) {
        BankBranch branch = bankBranchRepository.findByName(name);
        if (branch == null) {
            throw new NoSuchElementException("Bank branch not found with name: " + name);
        }
        return convertEntityToDTO(branch);
    }

    @Override
    public BankBranchDTO getBankBranchByIfscPrefix(String ifscPrefix) {
        BankBranch branch = bankBranchRepository.findByIfscPrefix(ifscPrefix);
        if (branch == null) {
            throw new NoSuchElementException("Bank branch not found with IFSC prefix: " + ifscPrefix);
        }
        return convertEntityToDTO(branch);
    }

    private BankBranchDTO convertEntityToDTO(BankBranch branch) {
        return new BankBranchDTO(branch.getBranchId(), branch.getName(), branch.getAddress(), branch.getIfscPrefix());
    }

    private BankBranch convertCreateRequestDTOtoEntity(BankBranchCreateRequestDTO createRequestDTO) {
        BankBranch branch = new BankBranch();
        branch.setName(createRequestDTO.getName());
        branch.setAddress(createRequestDTO.getAddress());
        branch.setIfscPrefix(createRequestDTO.getIfscPrefix());
        return branch;
    }
}