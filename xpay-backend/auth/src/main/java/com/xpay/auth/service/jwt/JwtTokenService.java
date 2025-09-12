package com.xpay.auth.service.jwt;

import com.xpay.auth.config.JwtConfiguration;
import com.xpay.auth.enums.UserRole;
import com.xpay.auth.enums.UserStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
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

    public String generateToken(String userId, String username, UserRole userRole, UserStatus userStatus) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_name", username);
        claims.put("user_role", userRole.name());
        claims.put("user_status", userStatus.name());
        return createToken(claims, userId);
    }

    private String createToken(Map<String, Object> claims, String userId) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .setSubject(userId)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token) {
       try {
           Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
           return !jwtParserService.isTokenExpired(token);
       } catch (Exception e) {
           return false;
       }
    }
}
