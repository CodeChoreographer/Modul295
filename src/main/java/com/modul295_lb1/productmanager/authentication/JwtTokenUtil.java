package com.modul295_lb1.productmanager.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "CédricsSichererSicherheitsschlüssel";
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // Token gültig für 5 Stunden

    // Extrahiert den Benutzernamen aus dem Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrahiert ein bestimmtes Claim aus dem Token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrahiert alle Claims aus dem Token
    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parser().setSigningKey(SECRET_KEY);
    }

    // Überprüft, ob das Token abgelaufen ist
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrahiert das Ablaufdatum des Tokens
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generiert ein Token für einen Benutzer
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Erstellt das Token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validiert das Token
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
}
