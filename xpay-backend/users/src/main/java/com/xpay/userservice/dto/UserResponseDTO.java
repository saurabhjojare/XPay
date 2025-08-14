package com.xpay.userservice.dto;

import com.xpay.userservice.enums.UserStatus;
import com.xpay.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String id;
    private UserType userType;
    private String name;
    private String email;
    private String phoneNumber;
    private String dateOfBirth; // Format: yyyy-MM-dd
    private String countryCode;
    private LocalDateTime createdAt;
    private UserStatus userStatus;
}
