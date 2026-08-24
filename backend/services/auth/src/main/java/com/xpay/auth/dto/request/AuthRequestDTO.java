package com.xpay.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for login request containing email and password
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
}
