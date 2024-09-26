package com.modul295_lb1.productmanager.resources.users;

import com.modul295_lb1.productmanager.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller für Benutzeroperationen (Registrierung, Authentifizierung, Abrufen, Bearbeiten).
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
     * @return Benutzer-Objekt
     */
    @PostMapping("register")
    public ResponseEntity<UserData> registerUser(@RequestBody UserData userData) {
        // Hier sicherstellen, dass die E-Mail nicht bereits existiert
        if (userService.userExists(userData.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Benutzername bereits vergeben
        }
        UserData createdUser = userService.registerUser(userData);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    /**
     * Authentifiziert einen Benutzer und gibt ein Authentifizierungstoken zurück.
     *
     * @param email Benutzername
     * @param password Passwort
     * @return Authentifizierungstoken
     */
    @PostMapping("login")
    public TokenWrapper authenticateUser(@RequestParam String email, @RequestParam String password) {
        UserData userData = userService.getUserByCredentials(email, password);
        if(userData != null) {
            TokenWrapper tokenWrapper = new TokenWrapper();
            String token = tokenService.generateToken(userData);
            tokenWrapper.setToken(token);
            return tokenWrapper;
        } else {
            return null;
        }
    }

    /**
     * Ruft die eigenen Benutzerdaten ab (geschützt durch JWT-Token).
     *
     * @param authorizationHeader Authorization-Header mit JWT-Token
     * @return Benutzer-Objekt
     */
    @GetMapping("/me")
    public ResponseEntity<UserData> getUserData(@RequestHeader("Authorization") String authorizationHeader) {
        // JWT-Token extrahieren
        String token = authorizationHeader.substring(7);  // Entfernt "Bearer "
        return null;
   }

    /**
     * Bearbeitet die Benutzerdaten für einen Benutzer mit einer bestimmten ID (geschützt durch JWT-Token).
     *
     * @param id Benutzer-ID
     * @param userData Aktualisierte Benutzerdaten
     * @param authorizationHeader Authorization-Header mit JWT-Token
     * @return Benutzer-Objekt
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserData> updateUser(
            @PathVariable Integer id,
            @RequestBody UserData userData,
            @RequestHeader("Authorization") String authorizationHeader) {

        // JWT-Token validieren und Benutzer authentifizieren
        String token = authorizationHeader.substring(7);  // Entfernt "Bearer "

        // Überprüfen, ob der Benutzer berechtigt ist, seine eigenen Daten zu ändern

            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Zugriff verweigert
        }
}
