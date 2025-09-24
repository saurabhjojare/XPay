package com.xpay.auth.mapper;

import com.xpay.auth.dto.UserRequestDTO;
import com.xpay.auth.dto.event.UserCreatedEventDTO;
import com.xpay.auth.model.User;

import java.time.LocalDateTime;

public class UserMapper {
    // Static factory method to create UserCreatedDTO
    public static UserCreatedEventDTO toUserCreatedDTO(
            User user, UserRequestDTO request, LocalDateTime localDateTime) {
        return new UserCreatedEventDTO(
                user.getUserId(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getCountryCode(),
                request.getPhoneNumber(),
                request.getDateOfBirth(),
                localDateTime,
                localDateTime
        );
    }
}
