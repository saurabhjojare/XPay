package com.xpay.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserRegistrationRequest {
    private final String username;
    private final String plainPassword;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String countryCode;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
}
