package com.example.aisaas.util;

import com.example.aisaas.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ✅ GENERATE TOKEN (FIXED)
    public String generateToken(String email, Role role, Long tenantId) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());
        claims.put("tenantId", tenantId);

        return Jwts.builder()
                .claims(claims) // ✅ replaces setClaims
                .subject(email) // ✅ replaces setSubject
                .issuedAt(new Date()) // ✅ replaces setIssuedAt
                .expiration(new Date(System.currentTimeMillis() + expiration)) // ✅ replaces setExpiration
                .signWith(getSigningKey())
                .compact();
    }

    // ✅ EXTRACT TENANT ID
    public Long extractTenantId(String token) {
        Claims claims = getClaims(token);
        return claims.get("tenantId", Long.class);
    }

    // ✅ EXTRACT EMAIL
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ EXTRACT ROLE
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // ✅ VALIDATE TOKEN
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ CORE METHOD (THIS WAS MISSING)
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}