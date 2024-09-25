package com.modul295_lb1.productmanager.resources.users;

import com.modul295_lb1.productmanager.authentication.JwtTokenUtil;
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
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param userData Benutzerdaten (Name, E-Mail, Passwort)
     * @return Benutzer-Objekt
     */
    @PostMapping("register")
    public ResponseEntity<UserData> registerUser(@RequestBody UserData userData) {
        System.out.println("register klappt");
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
    public ResponseEntity<String> authenticateUser(@RequestParam String email, @RequestParam String password) {
        System.out.println("hallo login klappt");
        String token = userService.authenticateUser(email, password);
        return ResponseEntity.ok("Bearer " + token);
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
        String username = jwtTokenUtil.extractUsername(token);

        // Benutzer anhand des Tokens abrufen
        UserData userData = userService.getUserDataByUsername(username);
        return ResponseEntity.ok(userData);
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
        String username = jwtTokenUtil.extractUsername(token);

        // Überprüfen, ob der Benutzer berechtigt ist, seine eigenen Daten zu ändern
        UserData currentUser = userService.getUserDataByUsername(username);
        if (!currentUser.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Zugriff verweigert
        }

        UserData updatedUser = userService.updateUser(id, userData);
        return ResponseEntity.ok(updatedUser);
    }
}
