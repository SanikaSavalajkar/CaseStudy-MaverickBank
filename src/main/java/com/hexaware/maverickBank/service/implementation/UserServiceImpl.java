package com.hexaware.maverickBank.service.implementation;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.LoginRequestDTO;
import com.hexaware.maverickBank.dto.UserDTO;
import com.hexaware.maverickBank.dto.UserRegistrationRequestDTO;
import com.hexaware.maverickBank.dto.UserUpdateRequestDTO;
import com.hexaware.maverickBank.entity.Role;
import com.hexaware.maverickBank.entity.User;
import com.hexaware.maverickBank.repository.IRoleRepository;
import com.hexaware.maverickBank.repository.IUserRepository;
import com.hexaware.maverickBank.service.interfaces.UserService;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public UserDTO registerUser(UserRegistrationRequestDTO registrationRequestDTO) {
        log.info("Registering user with username: {}", registrationRequestDTO.getUsername());

        if (userRepository.findByUsername(registrationRequestDTO.getUsername()) != null) {
            throw new ValidationException("Username already exists");
        }

        if (!isValidEmail(registrationRequestDTO.getEmail())) {
            throw new ValidationException("Invalid email format");
        }

        if (userRepository.findByEmail(registrationRequestDTO.getEmail()) != null) {
            throw new ValidationException("Email already exists");
        }

        if (!isValidPassword(registrationRequestDTO.getPassword())) {
            throw new ValidationException("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit");
        }

        User user = new User();
        user.setUsername(registrationRequestDTO.getUsername());
        user.setPassword(registrationRequestDTO.getPassword()); // No encoding
        user.setEmail(registrationRequestDTO.getEmail());

        Role role = (registrationRequestDTO.getRoleId() != null)
                ? roleRepository.findById(registrationRequestDTO.getRoleId()).orElse(roleRepository.findByName("CUSTOMER"))
                : roleRepository.findByName("CUSTOMER");

        user.setRole(role);

        User savedUser = userRepository.save(user);
        return convertUserToDTO(savedUser);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername());
        if (user == null || !user.getPassword().equals(loginRequestDTO.getPassword())) {
            throw new ValidationException("Invalid username or password");
        }
        return "Login successful"; // Just a dummy success response
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        return convertUserToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserUpdateRequestDTO updateRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        if (updateRequestDTO.getUsername() != null && !updateRequestDTO.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(updateRequestDTO.getUsername()) != null) {
                throw new ValidationException("Username already exists");
            }
            user.setUsername(updateRequestDTO.getUsername());
        }

        if (updateRequestDTO.getPassword() != null && !updateRequestDTO.getPassword().isEmpty()) {
            if (!isValidPassword(updateRequestDTO.getPassword())) {
                throw new ValidationException("Password must meet the required format");
            }
            user.setPassword(updateRequestDTO.getPassword());
        }

        if (updateRequestDTO.getEmail() != null && !updateRequestDTO.getEmail().equals(user.getEmail())) {
            if (!isValidEmail(updateRequestDTO.getEmail())) {
                throw new ValidationException("Invalid email format");
            }
            if (userRepository.findByEmail(updateRequestDTO.getEmail()) != null) {
                throw new ValidationException("Email already exists");
            }
            user.setEmail(updateRequestDTO.getEmail());
        }

        if (updateRequestDTO.getRoleId() != null) {
            Role role = roleRepository.findById(updateRequestDTO.getRoleId())
                    .orElseThrow(() -> new NoSuchElementException("Role not found with ID: " + updateRequestDTO.getRoleId()));
            user.setRole(role);
        }

        return convertUserToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    private UserDTO convertUserToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        if (user.getRole() != null) {
            dto.setRoleId(user.getRole().getRoleId());
        }
        return dto;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
