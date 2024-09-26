package com.modul295_lb1.productmanager.resources.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entitätsklasse, die einen Benutzer im System darstellt.
 * Mapped to the 'users' table in the database.
 */
@Setter
@Getter
@Entity
@Table(name = "users")
public class UserData {

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

    /**
     * Gibt die Rollen des Benutzers zurück.
     *
     * @return Liste von Rollen
     */
    public List<String> getRoles() {
        if (isAdmin) {
            return List.of("ROLE_ADMIN");
        }
        return new ArrayList<>();
    }
}
