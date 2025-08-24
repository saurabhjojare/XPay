package com.xpay.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenValidator  {
    private static final String SECRET_KEY = "a267206a66f9e659f8ebeaa75090e71b04a626165f1e874130b26a7309fe934e";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
}
