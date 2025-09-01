package com.xpay.auth.service.jwt;

import com.xpay.auth.config.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtParserService {
    private final JwtConfiguration jwtConfiguration;

    public JwtParserService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    // Helper method to parse the token and retrieve all claims
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfiguration.getJwtSecretKey().getBytes()))
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
