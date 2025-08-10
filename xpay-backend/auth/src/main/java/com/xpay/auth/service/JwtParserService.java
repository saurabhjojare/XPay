package com.xpay.auth.service;

import com.xpay.auth.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtParserService {
    private final JwtConfig jwtConfig;

    public JwtParserService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // Helper method to parse the token and retrieve all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Generic method to extract any type of claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final  Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract the expiration date from the JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    // Check if token has expired
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
