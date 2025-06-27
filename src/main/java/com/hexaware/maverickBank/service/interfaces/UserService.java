package com.hexaware.maverickBank.service.interfaces;

import com.hexaware.maverickBank.dto.LoginRequestDTO;
import com.hexaware.maverickBank.dto.UserDTO;
import com.hexaware.maverickBank.dto.UserRegistrationRequestDTO;
import com.hexaware.maverickBank.dto.UserUpdateRequestDTO;
import com.hexaware.maverickBank.entity.User;

public interface UserService {
    UserDTO registerUser(UserRegistrationRequestDTO registrationRequestDTO); // Use DTO for registration request
    UserDTO getUserById(Long userId); // Return DTO
    UserDTO updateUser(Long userId, UserUpdateRequestDTO updateRequestDTO); // Use DTO for update request
    
    void deleteUser(Long userId);
    String login(LoginRequestDTO loginRequestDTO);
	User getUserByUsername(String username);
}