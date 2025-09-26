package com.xpay.auth.dto;

import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserRequestDTO {
    private final String username;
    private final String plainPassword;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String countryCode;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final UserRole userRole;
    private final UserStatus userStatus;
}
