package com.modul295_lb1.productmanager.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

/**
 * Konfiguration der Web-Sicherheit für die Anwendung.
 * Diese Klasse definiert die Sicherheitsrichtlinien, einschließlich
 * Authentifizierung, Autorisierung und CORS-Konfiguration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Bean für den Passwort-Encoder.
     *
     * @return BCryptPasswordEncoder zur Verschlüsselung von Passwörtern.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Definiert die Sicherheitsrichtlinien für die HTTP-Anfragen.
     *
     * @param http das HttpSecurity-Objekt zur Konfiguration von Sicherheitseinstellungen.
     * @return das SecurityFilterChain-Objekt.
     * @throws Exception bei Konfigurationsfehlern.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() // Vorübergehend deaktiviert, passe bei Bedarf an
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/users/**").permitAll() // Öffentlich zugänglich
                .requestMatchers(HttpMethod.GET, "/products/**", "/categories/**").permitAll() // Öffentlich für GET-Anfragen
                .requestMatchers(HttpMethod.POST, "/products/**", "/categories/**").hasAuthority("ROLE_ADMIN") // Nur Admin für POST
                .requestMatchers(HttpMethod.PUT, "/users/{id}/").hasAuthority("ROLE_ADMIN") // Nur Admin für PUT
                .requestMatchers(HttpMethod.DELETE, "/products/**", "/categories/**").hasAuthority("ROLE_ADMIN") // Nur Admin für DELETE
                .requestMatchers("/users/me").authenticated() // Authentifizierung erforderlich
                .anyRequest().authenticated(); // Alle anderen Anforderungen erfordern Authentifizierung

        return http.build();
    }

    /**
     * Definiert den AuthenticationManager für die Anwendung.
     *
     * @param http das HttpSecurity-Objekt zur Konfiguration von Sicherheitseinstellungen.
     * @return der konfigurierte AuthenticationManager.
     * @throws Exception bei Konfigurationsfehlern.
     */
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }

    /**
     * Konfiguriert die CORS-Richtlinien für die Anwendung.
     *
     * @return eine CorsConfigurationSource mit den definierten CORS-Einstellungen.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("*")); // Erlaubt alle Ursprünge während der Entwicklung
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
