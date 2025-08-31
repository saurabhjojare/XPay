package com.xpay.auth.service.jwt;

import com.xpay.auth.config.JwtConfiguration;
import com.xpay.auth.enums.UserStatus;
import com.xpay.auth.enums.UserType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {
    private final JwtConfiguration jwtConfiguration;
    private final JwtParserService jwtParserService;
    private final Key key;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    public JwtTokenService(JwtConfiguration jwtConfiguration, JwtParserService jwtParserService) {
        this.jwtConfiguration = jwtConfiguration;
        this.jwtParserService = jwtParserService;
        this.key = Keys.hmacShaKeyFor(jwtConfiguration.getJwtSecretKey().getBytes());
    }

    public String generateToken(String userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", userId);
        claims.put("username", username);
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token) {
       try {
           Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
           return !jwtParserService.isTokenExpired(token);
       } catch (Exception e) {
           return false;
       }
    }
}
