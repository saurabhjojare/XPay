package com.xpay.userservice.service;

import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.utility.PaginationUtil.PaginatedResponse;

public interface UserService {
    PaginatedResponse<UserResponseDTO> getAllUsers(int page, int size, String sortBy, String sortDir);
    Boolean createUser(UserRequestDTO userRequestDTO);
    Boolean deleteUserById(String id);
    UserResponseDTO getUserById(String id);
    Boolean updateUserById(String id, UserRequestDTO userRequestDTO);
    Boolean login (String email, String password);
}
