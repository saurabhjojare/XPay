package com.xpay.auth.service.jwt;

import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtClaimService {
    private final JwtParserService jwtParserService;

    public JwtClaimService(JwtParserService jwtParserService) {
        this.jwtParserService = jwtParserService;
    }

    // Extract all claims once
    private Claims getAllClaims(String token) {
        return jwtParserService.extractAllClaims(token);
    }

    // Extract user ID from subject
    public String extractUserId(String token) {
        return getAllClaims(token).getSubject();
    }

    // Extract the username from the JWT token
    public String extractUsername(String token) {
        return getAllClaims(token).get("user_name", String.class);
    }

    // Extract user role from custom claim
    public UserRole extractUserRole(String token) {
        String role = getAllClaims(token).get("user_role", String.class);
        return UserRole.valueOf(role);
    }

    // Extract user status from custom claim
    public UserStatus extractUserStatus(String token) {
        String status = getAllClaims(token).get("user_status", String.class);
        return UserStatus.valueOf(status);
    }
}
