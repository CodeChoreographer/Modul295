package com.modul295_lb1.productmanager.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();  // CSRF-Schutz deaktivieren, da JWT verwendet wird

        http
                .httpBasic().disable()  // HTTP Basic Authentication deaktivieren
                .authorizeHttpRequests(authz -> authz
                        // Benutzerregistrierung und -login
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/users/login").permitAll()

                        // Benutzerdaten (Eigene Daten anzeigen und Admin-Berechtigungen)
                        .requestMatchers("/users/me").authenticated()
                        .requestMatchers("/users/{id}").hasAuthority("ROLE_ADMIN")

                        // Produkte (Erstellen, Bearbeiten und Löschen nur für Admins)
                        .requestMatchers(HttpMethod.POST,"/products").hasAuthority("ROLE_ADMIN")  // POST: Produkt hinzufügen nur für Admin
                        .requestMatchers("/products/{id}").permitAll()  // GET: Produktdetails für alle sichtbar
                        .requestMatchers( HttpMethod.PUT ,"/products/{id}").hasAuthority("ROLE_ADMIN")  // PUT: Nur Admin darf Produkte bearbeiten
                        .requestMatchers(HttpMethod.DELETE,"/products/{id}").hasAuthority("ROLE_ADMIN")  // DELETE: Nur Admin darf Produkte löschen
                        .requestMatchers("/products/**").permitAll()  // GET: Produktliste für alle

                        // Produktkategorien (Erstellen, Bearbeiten und Löschen nur für Admins)
                        .requestMatchers( HttpMethod.POST,"/categories").hasAuthority("ROLE_ADMIN")  // POST: Nur Admins dürfen Kategorien hinzufügen
                        .requestMatchers("/categories/{id}").permitAll()  // GET: Kategoriedetails für alle sichtbar
                        .requestMatchers(HttpMethod.PUT, "/categories/{id}").hasAuthority("ROLE_ADMIN") // PUT: Nur Admin darf Kategorien bearbeiten
                        .requestMatchers(HttpMethod.DELETE, "/categories/{id}").hasAuthority("ROLE_ADMIN")  // DELETE: Nur Admin darf Kategorien löschen
                        .requestMatchers("/categories/{id}/products").permitAll()  // GET: Alle Produkte in der Kategorie
                        .requestMatchers("/categories/**").permitAll()  // GET: Alle Kategorien

                        // API-Dokumentation öffentlich zugänglich machen
                        .requestMatchers("/swagger-ui/index.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()

                        // Alle anderen Anfragen müssen authentifiziert sein
                        .anyRequest().authenticated()
                );

        return http.build();  // SecurityFilterChain zurückgeben
    }
}
