package com.modul295_lb1.productmanager.resources.users;

import com.modul295_lb1.productmanager.auth.JwtRequestFilter;
import com.modul295_lb1.productmanager.auth.TokenService;
import com.modul295_lb1.productmanager.auth.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller für Benutzeroperationen wie Registrierung, Authentifizierung und Datenverwaltung.
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final JwtRequestFilter jwtRequestFilter;
    private final UserRepository userRepository; // userRepository hinzufügen

    @Autowired
    public UserController(UserService userService, TokenService tokenService, JwtRequestFilter jwtRequestFilter, UserRepository userRepository) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.userRepository = userRepository; // userRepository initialisieren
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param userData Benutzerdaten (Name, E-Mail, Passwort)
     * @return Benutzer-Objekt mit HTTP-Status 201 (Created) oder 409 (Conflict)
     */
    @PostMapping("register")
    @Operation(
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
     * @param loginRequest    Benutzername oder E-Mail und Passwort
     * @return Ein TokenWrapper-Objekt mit dem Authentifizierungstoken
     */
    @PostMapping("login")
    @Operation(
            summary = "Benutzer authentifizieren",
            description = "Authentifiziert einen Benutzer und gibt ein Token zurück."
    )
    public ResponseEntity<TokenWrapper> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Benutzerdaten mit den angegebenen Anmeldeinformationen abrufen
        UserData userData = userService.getUserByCredentials(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        if (userData != null) {
            // Token generieren und im Wrapper zurückgeben
            TokenWrapper tokenWrapper = new TokenWrapper();
            String token = tokenService.generateToken(userData);
            tokenWrapper.setToken(token);

            // Antwort mit Token und HTTP-Status 200 (OK)
            return ResponseEntity.ok(tokenWrapper);
        } else {
            // Bei ungültigen Anmeldeinformationen 401 (Unauthorized) zurückgeben
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Ruft die eigenen Benutzerdaten ab (geschützt durch JWT-Token).
     *
     * @param principal Authorization-Header mit JWT-Token
     * @return Benutzer-Objekt mit HTTP-Status 200 (OK) oder 401 (Unauthorized)
     */
    @GetMapping("me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserPrincipal principal) {
        UserDTO userDTO = userService.convertToDTO(principal.getUser());
        return ResponseEntity.ok(userDTO);
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
    @Operation(
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
