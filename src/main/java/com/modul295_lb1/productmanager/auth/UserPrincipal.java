package com.modul295_lb1.productmanager.auth;

import com.modul295_lb1.productmanager.resources.users.UserData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final UserData user;

    public UserPrincipal(UserData userData) {
        this.user = userData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Füge die Rolle je nach Admin-Status hinzu
        String role = user.getIsAdmin() ? "ROLE_ADMIN" : "ROLE_USER";
        authorities.add(new SimpleGrantedAuthority(role));

        return authorities;
    }

    @Override
    public String getPassword() {
        // Gib das tatsächliche Passwort aus dem UserData-Objekt zurück
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Gib den tatsächlichen Benutzernamen aus dem UserData-Objekt zurück
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
