package com.modul295_lb1.productmanager.resources.users;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * Konfigurationsklasse zur Initialisierung eines Standard-Admin-Benutzers.
 * Diese Klasse stellt sicher, dass beim Start der Anwendung ein Admin-Benutzer vorhanden ist.
 */
@Configuration
public class AdminInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Initialisiert einen Standard-Admin-Benutzer, wenn dieser noch nicht existiert.
     * Diese Methode wird nach der Konstruktion der Bean automatisch ausgeführt.
     *
     * <p>
     * Der Admin-Benutzer wird mit dem Benutzernamen "Admin", dem Passwort "Admin" (verschlüsselt),
     * der E-Mail-Adresse "admin@example.com" und den entsprechenden Rechten erstellt.
     * </p>
     */
    @PostConstruct
    public void initializeAdminUser() {
        // Überprüfen, ob der Admin-Benutzer bereits existiert
        Optional<UserData> existingAdmin = Optional.ofNullable(userRepository.findByUsername("Admin"));
        if (existingAdmin.isEmpty()) {
            // Admin-Benutzer erstellen
            UserData adminUser = new UserData();
            adminUser.setUsername("Admin");
            adminUser.setPassword(passwordEncoder.encode("Admin"));  // Passwort verschlüsseln
            adminUser.setEmail("admin@example.com");
            adminUser.setActive(true);
            adminUser.setIsAdmin(true);  // Admin-Rechte setzen

            // Admin-Benutzer in die Datenbank speichern
            userRepository.save(adminUser);
            System.out.println("Admin-Benutzer wurde erfolgreich erstellt!");
        } else {
            System.out.println("Admin-Benutzer existiert bereits.");
        }
    }

    /**
     * Stellt einen {@link PasswordEncoder} als Bean bereit, um Passwörter sicher zu verschlüsseln.
     *
     * @return Ein {@link PasswordEncoder} für die Verschlüsselung von Passwörtern.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
