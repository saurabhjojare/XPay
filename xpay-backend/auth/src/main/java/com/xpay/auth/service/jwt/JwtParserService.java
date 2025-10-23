package com.xpay.auth.service.jwt;

import com.xpay.auth.config.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class JwtParserService {
    private final JwtConfiguration jwtConfiguration;

    // Helper method to get the signing key from the secret
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfiguration.getJwtSecretKey().getBytes());
    }

    // Helper method to parse the token and retrieve all claims
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Generic method to extract any type of claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract the expiration date from the JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Check if token has expired
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
