package com.modul295_lb1.productmanager.authentication;

import com.modul295_lb1.productmanager.resources.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service-Klasse zur Benutzeranmeldung und -authentifizierung.
 * Implementiert das UserDetailsService-Interface, um Benutzerdaten zu laden.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Lädt den Benutzer anhand des Benutzernamens und gibt die Benutzerdetails zurück.
     *
     * @param email der Benutzername des anzumeldenden Benutzers.
     * @return UserDetails-Objekt mit den Benutzerinformationen.
     * @throws UsernameNotFoundException wenn der Benutzer nicht gefunden wird.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Benutzer nicht gefunden: " + email);
        }

        System.out.println("Benutzer gefunden: " + user.getEmail()); // Debug-Ausgabe

        // Erstelle eine Liste der Berechtigungen
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getIsAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorities
        );
    }
}
