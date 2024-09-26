package com.modul295_lb1.productmanager.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Konfiguriert die Sicherheitsrichtlinien für die Anwendung,
 * einschliesslich der Authentifizierung und Autorisierung von Benutzern.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Erstellt die Sicherheitsfilter für die Anwendung.
     *
     * @param http das HttpSecurity-Objekt zur Konfiguration
     * @return die konfigurierte SecurityFilterChain
     * @throws Exception Fehlermeldungen, wenn ein Fehler bei der Konfiguration auftritt
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // JWT-Filter vor dem Standard-Authentifizierungsfilter hinzufügen
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // CSRF-Schutz deaktivieren, da JWT verwendet wird
        http.csrf().disable();

        http
                .httpBasic().disable()  // HTTP Basic Authentication deaktivieren
                .authorizeHttpRequests(authz -> authz
                        // Erlaubt allen Benutzern die Registrierung und den Login
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/users/login").permitAll()

                        // Erfordert Authentifizierung für Benutzerdaten
                        .requestMatchers("/users/me").authenticated()
                        // Erfordert Admin-Rolle für Zugriff auf andere Benutzerdaten
                        .requestMatchers("/users/{id}").hasAuthority("ROLE_ADMIN")

                        // Produkte und Kategorien: Erlaubte Operationen basierend auf den Berechtigungen
                        .requestMatchers(HttpMethod.POST, "/products").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/products/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/categories/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/categories/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/categories/{id}/products").permitAll()
                        .requestMatchers("/categories/**").permitAll()

                        // API-Dokumentation ist öffentlich zugänglich
                        .requestMatchers("/swagger-ui/index.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()

                        // Alle anderen Anfragen müssen authentifiziert sein
                        .anyRequest().authenticated()
                );

        return http.build();  // Gibt die konfigurierte SecurityFilterChain zurück
    }
}
