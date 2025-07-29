package com.xpay.userservice.dto;

import com.xpay.userservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private UserType userType;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String dateOfBirth; // Format: yyyy-MM-dd
    private String countryCode;
}
