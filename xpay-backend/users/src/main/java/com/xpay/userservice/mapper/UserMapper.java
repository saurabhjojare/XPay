package com.xpay.userservice.mapper;

import com.xpay.userservice.dto.UserRequestDTO;
import com.xpay.userservice.dto.UserResponseDTO;
import com.xpay.userservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User toUserMapper(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) return null;

        return User.builder()
                .userType(userRequestDTO.getUserType())
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .password(userRequestDTO.getPassword())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .countryCode(userRequestDTO.getCountryCode())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserResponseDTO userResponseMapper(User user) {
        if (user == null) return null;
        return UserResponseDTO.builder()
                .id(user.getId())
                .userType(user.getUserType())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .countryCode(user.getCountryCode())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public void updateUserFromDto(UserRequestDTO dto, User user) {
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getUserType() != null) user.setUserType(dto.getUserType());
        if (dto.getDateOfBirth() != null) user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getCountryCode() != null) user.setCountryCode(dto.getCountryCode());

        user.setUpdatedAt(LocalDateTime.now());
    }
}
