package com.xpay.userservice.service.interfaces;

import com.xpay.userservice.dto.request.UserRequestDTO;
import com.xpay.userservice.dto.response.UserResponseDTO;
import com.xpay.userservice.utility.PaginationUtil.PaginatedResponse;
import java.util.UUID;

public interface UserService {
    PaginatedResponse<UserResponseDTO> getAllUsers(int page, int size, String sortBy, String sortDir);
    Boolean createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(UUID userId);
    boolean deleteUserByUserId(UUID userId);
}
