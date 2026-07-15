package com.xpay.transactions.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret-key}")
    private String secretKey;

    // Extract Claims from token
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    // Extract User ID (sub)
    public String getUserId(String token) {
        return getClaims(token).getSubject();
    }

    // Extract Role
    public String getUserRole(String token) {
        return (String) getClaims(token).get("user_role");
    }

    // Extract Status
    public String getUserStatus(String token) {
        return (String) getClaims(token).get("user_status");
    }

    // Extract Expiration Date
    public Date getExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    // Check if token expired
    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
