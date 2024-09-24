package com.modul295_lb1.productmanager.resources.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service für Benutzeroperationen.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param userData Benutzerdaten
     * @return Erstelltes Benutzerobjekt
     */
    public UserData registerUser(UserData userData) {
        if (userRepository.findByUsername(userData.getUsername()) != null) {
            throw new RuntimeException("Benutzer existiert bereits"); // Fehlerbehandlung
        }
        return userRepository.save(userData);
    }

    /**
     * Authentifiziert einen Benutzer und gibt ein Token zurück.
     *
     * @param username Benutzername
     * @param password Passwort
     * @return Authentifizierungstoken
     */
    public String authenticateUser(String username, String password) {
        UserData user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "auth_token"; // Hier könnte ein echtes Token generiert werden
        }
        throw new RuntimeException("Ungültige Anmeldeinformationen"); // Fehlerbehandlung
    }

    /**
     * Ruft die aktuellen Benutzerdaten ab.
     *
     * @return Benutzerobjekt
     */
    public UserData getCurrentUserData() {
        // Hier sollte die Benutzer-ID aus der Authentifizierung abgerufen werden.
        // Dies ist ein Platzhalter für die Logik.
        Integer currentUserId = 1; // Platzhalter-ID
        return userRepository.findById(currentUserId).orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
    }

    /**
     * Bearbeitet die Benutzerdaten eines Benutzers mit einer bestimmten ID.
     *
     * @param id Benutzer-ID
     * @param userData Aktualisierte Benutzerdaten
     * @return Aktualisiertes Benutzerobjekt
     */
    public UserData updateUser(Integer id, UserData userData) {
        UserData existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
        existingUser.setUsername(userData.getUsername());
        existingUser.setEmail(userData.getEmail());
        existingUser.setActive(userData.getActive());
        existingUser.setIsAdmin(userData.getIsAdmin());
        return userRepository.save(existingUser);
    }
}
