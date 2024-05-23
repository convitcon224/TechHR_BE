package com.usth.techhr.techhr.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.usth.techhr.techhr.security.SecurityConstant.JWT_TOKEN_EXPIRATION_TIME;

@Component
public class JwtTokenGenerator {
    private static final String JWT_SECRET = "Bgq{BhKZA<DyQ5YnelJ78=gI4PEK?Qz</0W_Ud_3_SSSTZ<%-~<zG2*SGh8_X])";
    private final SecretKey secretKey;

    public JwtTokenGenerator() {
        this.secretKey = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_TOKEN_EXPIRATION_TIME);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(this.secretKey)
                .compact();

        return token;
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token was expired or incorrect");
        }
    }
}
