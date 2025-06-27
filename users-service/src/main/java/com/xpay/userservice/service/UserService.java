package com.xpay.userservice.service;

import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    void deleteUserById(String id);
    Optional<UserResponseDTO> getUserById(String id);
}
