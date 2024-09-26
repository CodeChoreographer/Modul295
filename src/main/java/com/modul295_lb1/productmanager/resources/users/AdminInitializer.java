package com.modul295_lb1.productmanager.resources.users;

import com.modul295_lb1.productmanager.resources.users.UserData;
import com.modul295_lb1.productmanager.resources.users.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Configuration
public class AdminInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeAdminUser() {
        // Überprüfen, ob der Admin-Benutzer bereits existiert
        Optional<UserData> existingAdmin = Optional.ofNullable((userRepository.findByUsername("Admin")));
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

    // Passwort-Encoder als Bean bereitstellen
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
