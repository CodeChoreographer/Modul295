//package com.modul295_lb1.productmanager.resources.users;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Controller für die Benutzeranmeldung.
// */
//@RestController
//@RequestMapping("/authtest")
//public class AuthController {
//
//    private final UserService userService;
//
//    @Autowired
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserData userData) {
//        try {
//            userService.registerUser(userData);
//            return ResponseEntity.ok("Benutzerregistrierung erfolgreich.");
//        } catch (UserService.UserAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Hier wird die Fehlermeldung zurückgegeben
//        }
//    }
//
//
//
//    /**
//     * Authentifiziert den Benutzer und gibt ein Authentifizierungstoken zurück.
//     *
//     * @param loginRequest Die Anmeldeinformationen des Benutzers
//     * @return ResponseEntity mit dem Token oder einer Fehlermeldung
//     */
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            String token = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
//            return ResponseEntity.ok("Benutzeranmeldung erfolgreich. Token: " + token);
//        } catch (UserService.InvalidCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ungültige Anmeldeinformationen");
//        }
//    }
//}
