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
        if (userRepository.findByUsername("Admin") == null) {
            UserData adminUser = new UserData();
            adminUser.setUsername("Admin");
            adminUser.setPassword("Admin");
            adminUser.setActive(true);
            adminUser.setEmail("admin@example.com");
            adminUser.setIsAdmin(true);
            userRepository.save(adminUser);
        }
    }
}
