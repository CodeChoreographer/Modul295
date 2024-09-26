//package com.modul295_lb1.productmanager.resources.users;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdminUserInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AdminUserInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) {
//        createAdminUser();
//    }
//
//    /**
//     * Erstellt einen Admin-Benutzer, wenn noch keiner vorhanden ist.
//     */
//    private void createAdminUser() {
//        // Überprüfen, ob bereits ein Benutzer mit Admin-Rechten existiert
//        if (userRepository.findByUsername("Admin") == null) {
//            UserData adminUser = new UserData();
//            adminUser.setUsername("Admin");
//
//            // Passwort wird gehashed, um die Sicherheit zu gewährleisten
//            adminUser.setPassword(passwordEncoder.encode("Admin")); // Passwort verschlüsseln
//
//            adminUser.setActive(true);
//            adminUser.setEmail("admin@example.com");
//            adminUser.setIsAdmin(true);
//
//            // Admin-Benutzer wird gespeichert
//            userRepository.save(adminUser);
//
//            System.out.println("Admin-Benutzer wurde erstellt.");
//        } else {
//            System.out.println("Admin-Benutzer existiert bereits.");
//        }
//    }
//}
