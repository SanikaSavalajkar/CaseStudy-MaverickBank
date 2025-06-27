package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the AdminService interface and manages
 *               the business logic related to user and bank employee management, including:
 * 
 *               User Management:
 *               - Creating users with encoded passwords and assigned roles
 *               - Retrieving users by ID
 *               - Listing all users
 *               - Updating user details including email, password, and role
 *               - Deleting users by ID
 * 
 *               Bank Employee Management:
 *               - Creating bank employees linked to users
 *               - Retrieving bank employees by employee ID
 *               - Listing all bank employees
 *               - Updating bank employee details
 *               - Deleting bank employees by ID
 * 
 *               The class performs necessary validations such as role and user existence,
 *               handles entity-to-DTO and DTO-to-entity conversions,
 *               and logs key actions for traceability.
 * -----------------------------------------------------------------------------
 */

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.BankEmployeeCreateRequestDTO;
import com.hexaware.maverickBank.dto.BankEmployeeDTO;
import com.hexaware.maverickBank.dto.BankEmployeeUpdateRequestDTO;
import com.hexaware.maverickBank.dto.UserDTO;
import com.hexaware.maverickBank.dto.UserRegistrationRequestDTO;
import com.hexaware.maverickBank.dto.UserUpdateRequestDTO;
import com.hexaware.maverickBank.entity.BankEmployee;
import com.hexaware.maverickBank.entity.User;
import com.hexaware.maverickBank.repository.IBankEmployeeRepository;
import com.hexaware.maverickBank.repository.IRoleRepository;
import com.hexaware.maverickBank.repository.IUserRepository;
import com.hexaware.maverickBank.service.interfaces.AdminService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private IBankEmployeeRepository bankEmployeeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    // User Management

    @Override
    public UserDTO createUser(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        log.info("Creating user with username: {}", userRegistrationRequestDTO.getUsername());
        User user = new User();
        BeanUtils.copyProperties(userRegistrationRequestDTO, user);

        // Set raw password (not encrypted)
        user.setPassword(userRegistrationRequestDTO.getPassword());

        // Set the role
        roleRepository.findById(userRegistrationRequestDTO.getRoleId()).ifPresentOrElse(user::setRole, () -> {
            throw new RuntimeException("Role not found with ID: " + userRegistrationRequestDTO.getRoleId());
        });

        User savedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, userDTO);
        if (savedUser.getRole() != null) {
            userDTO.setRoleId(savedUser.getRole().getRoleId());
        }
        log.info("User created successfully with ID: {}", userDTO.getUserId());
        return userDTO;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        log.info("Fetching user by ID: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userOptional.get(), userDTO);
            if (userOptional.get().getRole() != null) {
                userDTO.setRoleId(userOptional.get().getRole().getRoleId());
            }
            log.info("User found with ID: {}", userId);
            return userDTO;
        } else {
            log.warn("User not found with ID: {}", userId);
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            if (user.getRole() != null) {
                userDTO.setRoleId(user.getRole().getRoleId());
            }
            return userDTO;
        }).collect(Collectors.toList());
        log.info("Fetched {} users", userDTOs.size());
        return userDTOs;
    }

    @Override
    public UserDTO updateUser(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) {
        log.info("Updating user with ID: {} and data: {}", userId, userUpdateRequestDTO);
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(userUpdateRequestDTO.getEmail());

            if (userUpdateRequestDTO.getPassword() != null) {
                user.setPassword(userUpdateRequestDTO.getPassword());
            }

            if (userUpdateRequestDTO.getRoleId() != null) {
                roleRepository.findById(userUpdateRequestDTO.getRoleId()).ifPresentOrElse(user::setRole, () -> {
                    log.warn("Role not found with ID: {}", userUpdateRequestDTO.getRoleId());
                });
            }

            user.setUserId(userId);
            User updatedUser = userRepository.save(user);
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(updatedUser, userDTO);
            if (updatedUser.getRole() != null) {
                userDTO.setRoleId(updatedUser.getRole().getRoleId());
            }
            log.info("User with ID {} updated successfully", userId);
            return userDTO;
        } else {
            log.warn("User not found with ID: {}", userId);
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        log.info("User with ID {} deleted successfully", userId);
    }

    // Bank Employee Management

    @Override
    public BankEmployeeDTO createBankEmployee(BankEmployeeCreateRequestDTO dto) {
        log.info("Creating bank employee with user ID: {}", dto.getUserId());
        BankEmployee bankEmployee = new BankEmployee();
        BeanUtils.copyProperties(dto, bankEmployee);

        userRepository.findById(dto.getUserId()).ifPresentOrElse(bankEmployee::setUserId, () -> {
            throw new RuntimeException("User not found with ID: " + dto.getUserId());
        });

        BankEmployee saved = bankEmployeeRepository.save(bankEmployee);
        BankEmployeeDTO response = new BankEmployeeDTO();
        BeanUtils.copyProperties(saved, response);

        if (saved.getUserId() != null) {
            response.setUserId(saved.getUserId().getUserId());
        }

        log.info("Bank employee created with ID: {}", response.getEmployeeId());
        return response;
    }

    @Override
    public BankEmployeeDTO getBankEmployeeById(Long employeeId) {
        log.info("Fetching bank employee by ID: {}", employeeId);
        Optional<BankEmployee> bankEmployeeOptional = bankEmployeeRepository.findById(employeeId);
        if (bankEmployeeOptional.isPresent()) {
            BankEmployeeDTO dto = new BankEmployeeDTO();
            BeanUtils.copyProperties(bankEmployeeOptional.get(), dto);
            if (bankEmployeeOptional.get().getUserId() != null) {
                dto.setUserId(bankEmployeeOptional.get().getUserId().getUserId());
            }
            return dto;
        } else {
            log.warn("Bank employee not found with ID: {}", employeeId);
            return null;
        }
    }

    @Override
    public List<BankEmployeeDTO> getAllBankEmployees() {
        log.info("Fetching all bank employees");
        return bankEmployeeRepository.findAll().stream().map(emp -> {
            BankEmployeeDTO dto = new BankEmployeeDTO();
            BeanUtils.copyProperties(emp, dto);
            if (emp.getUserId() != null) {
                dto.setUserId(emp.getUserId().getUserId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public BankEmployeeDTO updateBankEmployee(Long employeeId, BankEmployeeUpdateRequestDTO dto) {
        log.info("Updating bank employee with ID: {}", employeeId);
        Optional<BankEmployee> optional = bankEmployeeRepository.findById(employeeId);
        if (optional.isPresent()) {
            BankEmployee emp = optional.get();
            BeanUtils.copyProperties(dto, emp);
            emp.setEmployeeId(employeeId);

            BankEmployee updated = bankEmployeeRepository.save(emp);
            BankEmployeeDTO response = new BankEmployeeDTO();
            BeanUtils.copyProperties(updated, response);
            if (updated.getBranch() != null) {
                response.setBranchId(updated.getBranch().getBranchId());
            }
            if (updated.getUserId() != null) {
                response.setUserId(updated.getUserId().getUserId());
            }
            return response;
        } else {
            log.warn("Bank employee not found with ID: {}", employeeId);
            return null;
        }
    }

    @Override
    public void deleteBankEmployee(Long employeeId) {
        log.info("Deleting bank employee with ID: {}", employeeId);
        bankEmployeeRepository.deleteById(employeeId);
    }
}
