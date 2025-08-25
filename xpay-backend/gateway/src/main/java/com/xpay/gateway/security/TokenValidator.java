package com.xpay.gateway.security;

public interface TokenValidator {
    boolean validateToken(String token);
    String extractUsername(String token);
}