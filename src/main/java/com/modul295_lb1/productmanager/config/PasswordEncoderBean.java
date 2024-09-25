package com.modul295_lb1.productmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderBean {

    private static BCryptPasswordEncoder encoderInstane;

    @Bean
    @Primary
    public PasswordEncoder getPasswordEncoder() {
        if (encoderInstane == null) {
            encoderInstane = new BCryptPasswordEncoder();
        }
        return encoderInstane;
    }
}
