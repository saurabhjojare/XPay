package com.xpay.auth.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtClaimService {
    private final JwtParserService jwtParserService;

    public JwtClaimService(JwtParserService jwtParserService) {
        this.jwtParserService = jwtParserService;
    }

    // Extract the username from the JWT token
    public String extractUsername(String token) {
        return jwtParserService.extractClaim(token, Claims::getSubject);
    }

    // Extract user ID from custom claim
    public Long extractUserId(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("id", Long.class));
    }

    // Extract user role from custom claim
    public String extractUserRole(String token) {
        return jwtParserService.extractClaim(token, claims -> claims.get("role", String.class));
    }
}
