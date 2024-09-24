package com.modul295_lb1.productmanager.resources.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public AdminUserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        createAdminUser();
    }

    private void createAdminUser() {
        // Überprüfen, ob der Admin-Benutzer bereits existiert
        if (userRepository.findByUsername("Admin") == null) {
            UserData adminUser = new UserData();
            adminUser.setUsername("Admin");
            adminUser.setPassword("Admin"); // Im Klartext, für Testzwecke
            adminUser.setActive(true);
            adminUser.setEmail("admin@example.com"); // Beispiel-Email
            adminUser.setIsAdmin(true); // Admin-Flag setzen
            userRepository.save(adminUser); // Admin-Benutzer speichern
        }
    }
}
