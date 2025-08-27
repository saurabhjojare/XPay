package com.xpay.gateway.security;

import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;

@Component
public class TokenExtractor {
    public String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
