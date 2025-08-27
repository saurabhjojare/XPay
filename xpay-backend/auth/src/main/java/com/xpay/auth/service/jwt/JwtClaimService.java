package com.xpay.auth.service.jwt;

import com.xpay.auth.enums.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class JwtClaimService {
    private final JwtParserService jwtParserService;

    public JwtClaimService(JwtParserService jwtParserService) {
        this.jwtParserService = jwtParserService;
    }

    // Extract the username from the JWT token
    public String extractEmail(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("email", String.class));
    }

    // Extract user ID from custom claim
    public String extractUserId(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("id", String.class));
    }

    // Extract user role from custom claim
    public String extractUserRole(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("user_type", String.class));
    }

    public String extractUserStatus(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("user_status", String.class));
    }
}
