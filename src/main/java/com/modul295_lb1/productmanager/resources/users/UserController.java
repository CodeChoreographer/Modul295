package com.modul295_lb1.productmanager.resources.users;

import org.springframework.web.bind.annotation.*;

/**
 * Controller für Benutzeroperationen (Registrierung, Abrufen, Bearbeiten).
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Registriert einen neuen Benutzer.
     *
     * @return String, der die erfolgreiche Registrierung anzeigt
     */
    @PostMapping("/")
    public String registerUser() {
        return "Benutzerregistrierung erfolgreich";
    }

    /**
     * Ruft die eigenen Benutzerdaten ab.
     *
     * @return String, der den Abruf der Benutzerdaten anzeigt
     */
    @GetMapping("/me")
    public String getUserData() {
        return "Benutzerdaten abgerufen";
    }

    /**
     * Bearbeitet die Benutzerdaten für einen Benutzer mit einer bestimmten ID.
     *
     * @param id Benutzer-ID
     * @return String, der die erfolgreiche Aktualisierung anzeigt
     */
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id) {
        return "Benutzerdaten von Benutzer " + id + " aktualisiert";
    }
}
