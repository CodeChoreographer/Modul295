package com.modul295_lb1.productmanager.resources.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller für Benutzeroperationen (Registrierung, Abrufen, Bearbeiten).
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param userData Benutzerdaten (Name, E-Mail, Passwort)
     * @return Benutzer-Objekt
     */
    @PostMapping("/")
    public ResponseEntity<UserData> registerUser(@RequestBody UserData userData) {
        UserData createdUser = userService.registerUser(userData);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Authentifiziert einen Benutzer und gibt ein Authentifizierungstoken zurück.
     *
     * @param username Benutzername
     * @param password Passwort
     * @return Authentifizierungstoken
     */
    @PostMapping("/auth/login/")
    public ResponseEntity<String> authenticateUser(@RequestParam String username, @RequestParam String password) {
        String token = userService.authenticateUser(username, password);
        return ResponseEntity.ok(token);
    }

    /**
     * Ruft die eigenen Benutzerdaten ab.
     *
     * @return Benutzer-Objekt
     */
    @GetMapping("/me")
    public ResponseEntity<UserData> getUserData() {
        UserData userData = userService.getCurrentUserData();
        return ResponseEntity.ok(userData);
    }

    /**
     * Bearbeitet die Benutzerdaten für einen Benutzer mit einer bestimmten ID.
     *
     * @param id Benutzer-ID
     * @param userData Aktualisierte Benutzerdaten
     * @return Benutzer-Objekt
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserData> updateUser(@PathVariable Integer id, @RequestBody UserData userData) {
        UserData updatedUser = userService.updateUser(id, userData);
        return ResponseEntity.ok(updatedUser);
    }
}
