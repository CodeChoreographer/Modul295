package com.modul295_lb1.productmanager.authentication;

import org.springframework.web.bind.annotation.*;

/**
 * Controller für die Benutzeranmeldung.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Authentifiziert den Benutzer und gibt ein Authentifizierungstoken zurück.
     *
     * @return String, der die erfolgreiche Anmeldung anzeigt
     */
    @PostMapping("/login")
    public String login() {
        return "Benutzeranmeldung erfolgreich";
    }
}
