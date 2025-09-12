package com.xpay.userservice.service;

import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.utility.PaginationUtil.PaginatedResponse;

import java.util.UUID;

public interface UserService {
    PaginatedResponse<UserResponseDTO> getAllUsers(int page, int size, String sortBy, String sortDir);
    Boolean createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(UUID userId);
    Boolean updateUserById(String id, UserRequestDTO userRequestDTO);
    void deleteUserByUserId(UUID userId);
}
