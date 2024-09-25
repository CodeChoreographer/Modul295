package com.modul295_lb1.productmanager.resources.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Entitätsklasse, die einen Benutzer im System darstellt.
 * Sie wird mit der Tabelle 'users' in der Datenbank gemappt.
 */
@Setter
@Getter
@Entity
@Table(name = "users")
public class UserData {

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean active;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isAdmin = false;

    public UserData() {
    }

    public UserData(String username, String password, Boolean active, String email, Boolean isAdmin) {
        this.username = username;
        this.password = password; // Das Passwort wird in der UserService gehashed
        this.active = active;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
