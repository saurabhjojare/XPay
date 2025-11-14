package com.xpay.auth.mapper;

import com.xpay.auth.dto.request.UserRequestDTO;
import com.xpay.auth.dto.event.UserCreatedEventDTO;
import com.xpay.auth.model.Users;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserCreatedEventDTO toUserCreatedDTO
            (Users user, UserRequestDTO request, LocalDateTime localDateTime) {
        // Create an empty object
        UserCreatedEventDTO dto = new UserCreatedEventDTO();

        // Fill it with data step by step
        dto.setUserId(user.getUserId());
        dto.setFirstName(request.getFirstName());
        dto.setLastName(request.getLastName());
        dto.setEmail(request.getEmail());
        dto.setCountryCode(request.getCountryCode());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setDateOfBirth(request.getDateOfBirth());
        dto.setCreatedAt(localDateTime);
        dto.setUpdatedAt(localDateTime);

        // Return the filled object
        return dto;
    }
}
