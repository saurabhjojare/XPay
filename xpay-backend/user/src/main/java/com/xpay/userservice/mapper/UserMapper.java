package com.xpay.userservice.mapper;

import com.xpay.userservice.dto.request.UserRequestDTO;
import com.xpay.userservice.dto.response.UserResponseDTO;
import com.xpay.userservice.dto.event.UserCreatedEventDTO;
import com.xpay.userservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toUserMapper(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) return null;
        User user = new User();
        user.setUserId(userRequestDTO.getUserId());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setDateOfBirth(userRequestDTO.getDateOfBirth());
        user.setCountryCode(userRequestDTO.getCountryCode());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public UserResponseDTO userResponseMapper(User user) {
        if (user == null) return null;
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUserId(user.getUserId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setCountryCode(user.getCountryCode());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    public UserRequestDTO userRequestMapper(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) return null;
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUserId(userRequestDTO.getUserId());
        dto.setFirstName(userRequestDTO.getFirstName());
        dto.setLastName(userRequestDTO.getLastName());
        dto.setEmail(userRequestDTO.getEmail());
        dto.setCountryCode(userRequestDTO.getCountryCode());
        dto.setPhoneNumber(userRequestDTO.getPhoneNumber());
        dto.setDateOfBirth(userRequestDTO.getDateOfBirth());
        dto.setCreatedAt(userRequestDTO.getCreatedAt());
        dto.setUpdatedAt(userRequestDTO.getUpdatedAt());
        return dto;
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

    public UserRequestDTO userRequestMapperFromEvent(UserCreatedEventDTO event) {
        if (event == null) return null;
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUserId(event.getUserId());
        dto.setFirstName(event.getFirstName());
        dto.setLastName(event.getLastName());
        dto.setEmail(event.getEmail());
        dto.setCountryCode(event.getCountryCode());
        dto.setPhoneNumber(event.getPhoneNumber());
        dto.setDateOfBirth(event.getDateOfBirth());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        return dto;
    }

}
