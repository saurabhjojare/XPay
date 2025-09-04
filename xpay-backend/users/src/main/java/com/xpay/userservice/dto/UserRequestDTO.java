package com.xpay.userservice.dto;

import com.xpay.userservice.enums.UserStatus;
import com.xpay.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private LocalDate dateOfBirth; // Format: yyyy-MM-dd
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
