package com.modul295_lb1.productmanager.auth;

import com.modul295_lb1.productmanager.resources.users.UserData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Dienstklasse zur Verwaltung von JWT-Token.
 * Diese Klasse bietet Funktionen zur Generierung von Token für Benutzer.
 */
@Service
public class TokenService {

    // Geheimschlüssel für die Token-Signierung
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Generiert ein JWT-Token für den angegebenen Benutzer.
     *
     * @param userData die Benutzerdaten, die für die Token-Generierung verwendet werden
     * @return das generierte JWT-Token als String
     */
    @Operation(summary = "Generiert ein JWT-Token für den Benutzer",
            description = "Erstellt ein JWT-Token, das die E-Mail des Benutzers und seine Rollen enthält.")
    public String generateToken(UserData userData) {
        return Jwts.builder()
                .setSubject(userData.getEmail()) // Setzt die Benutzer-E-Mail als Subjekt des Tokens
                .claim("username", userData.getUsername()) // Fügt Username des Benutzers hinzu
                .claim("roles", userData.getRoles())  // Fügt die Rollen des Benutzers hinzu
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Setzt das Ausstellungsdatum
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Setzt das Ablaufdatum auf 10 Stunden
                .signWith(SECRET_KEY)  // Signiert das Token mit dem Geheimschlüssel
                .compact();  // Kompaktierung des Tokens
    }

    /**
     * Gibt den geheimen Schlüssel zurück, der für die Token-Signierung verwendet wird.
     *
     * @return der geheime Schlüssel
     */
    @Operation(summary = "Gibt den geheimen Schlüssel zurück",
            description = "Ruft den geheimen Schlüssel ab, der für die Signierung von JWT-Token verwendet wird.")
    public SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
