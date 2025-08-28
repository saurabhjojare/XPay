package com.xpay.gateway.security;

@FunctionalInterface
public interface TokenValidator {
    boolean validateToken(String token);
}