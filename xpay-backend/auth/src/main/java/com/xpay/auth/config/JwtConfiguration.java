package com.xpay.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Holds JWT-related configuration properties
@Component
public class JwtConfiguration {
    // JWT secret key
    private final String jwtSecretKey;

    // Injects the secret key from application.properties
    public JwtConfiguration(@Value("${jwt.secret-key}") String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    // Returns the JWT secret key
    public String getJwtSecretKey() {
        return jwtSecretKey;
    }
}