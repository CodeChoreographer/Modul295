package com.modul295_lb1.productmanager.resources.users;

import com.modul295_lb1.productmanager.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller für Benutzeroperationen wie Registrierung, Authentifizierung und Datenverwaltung.
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param userData Benutzerdaten (Name, E-Mail, Passwort)
     * @return Benutzer-Objekt mit HTTP-Status 201 (Created) oder 409 (Conflict)
     */
    @PostMapping("register")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Benutzer registrieren",
            description = "Registriert einen neuen Benutzer mit den bereitgestellten Daten."
    )
    public ResponseEntity<UserData> registerUser(@RequestBody UserData userData) {
        // Überprüfen, ob die E-Mail bereits existiert
        if (userService.userExists(userData.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Benutzername bereits vergeben
        }
        UserData createdUser = userService.registerUser(userData);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Authentifiziert einen Benutzer und gibt ein Authentifizierungstoken zurück.
     *
     * @param email    Benutzername (E-Mail)
     * @param password Passwort
     * @return Ein TokenWrapper-Objekt mit dem Authentifizierungstoken
     */
    @PostMapping("login")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Benutzer authentifizieren",
            description = "Authentifiziert einen Benutzer und gibt ein Token zurück."
    )
    public TokenWrapper authenticateUser(@RequestParam String email, @RequestParam String password) {
        UserData userData = userService.getUserByCredentials(email, password);
        if (userData != null) {
            TokenWrapper tokenWrapper = new TokenWrapper();
            String token = tokenService.generateToken(userData);
            tokenWrapper.setToken(token);
            return tokenWrapper;
        } else {
            return null; // Benutzer nicht gefunden
        }
    }

    /**
     * Ruft die eigenen Benutzerdaten ab (geschützt durch JWT-Token).
     *
     * @param authorizationHeader Authorization-Header mit JWT-Token
     * @return Benutzer-Objekt mit HTTP-Status 200 (OK) oder 401 (Unauthorized)
     */
    @GetMapping("/me")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Benutzerdaten abrufen",
            description = "Ruft die Benutzerdaten des authentifizierten Benutzers ab."
    )
    public ResponseEntity<UserData> getUserData(@RequestHeader("Authorization") String authorizationHeader) {
        // JWT-Token extrahieren
        String token = authorizationHeader.substring(7); // Entfernt "Bearer "
        return null; // Implementierung fehlt
    }

    /**
     * Bearbeitet die Benutzerdaten für einen Benutzer mit einer bestimmten ID (geschützt durch JWT-Token).
     *
     * @param id                   Benutzer-ID
     * @param userData            Aktualisierte Benutzerdaten
     * @param authorizationHeader  Authorization-Header mit JWT-Token
     * @return Benutzer-Objekt mit HTTP-Status 200 (OK) oder 403 (Forbidden)
     */
    @PutMapping("/{id}")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Benutzerdaten bearbeiten",
            description = "Bearbeitet die Benutzerdaten des Benutzers mit der angegebenen ID."
    )
    public ResponseEntity<UserData> updateUser(
            @PathVariable Integer id,
            @RequestBody UserData userData,
            @RequestHeader("Authorization") String authorizationHeader) {

        return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Zugriff verweigert
    }
}
