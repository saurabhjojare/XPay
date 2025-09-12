package com.xpay.auth.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserCreatedEventDTO {
    private final UUID userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String countryCode;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}

