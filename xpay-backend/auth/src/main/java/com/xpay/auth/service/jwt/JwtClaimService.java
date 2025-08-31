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
    public String extractUsername(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("username", String.class));
    }

    // Extract user ID from custom claim
    public String extractUserId(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("user_id", String.class));
    }

}
