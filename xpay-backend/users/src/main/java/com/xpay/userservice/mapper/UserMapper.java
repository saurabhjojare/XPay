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
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .phoneNumber(userRequestDTO.getPhoneNumber())
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
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .countryCode(user.getCountryCode())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserRequestDTO userRequestMapper(UserRequestDTO userRequestDTO) {
        return UserRequestDTO.builder()
                .userId(userRequestDTO.getUserId())
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .countryCode(userRequestDTO.getCountryCode())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .createdAt(userRequestDTO.getCreatedAt())
                .updatedAt(userRequestDTO.getUpdatedAt())
                .build();
    }

    public void updateUserFromDto(UserRequestDTO dto, User user) {
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getDateOfBirth() != null) user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getCountryCode() != null) user.setCountryCode(dto.getCountryCode());

        user.setUpdatedAt(LocalDateTime.now());
    }
}
