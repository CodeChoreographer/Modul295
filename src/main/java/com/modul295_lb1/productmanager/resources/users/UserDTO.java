package com.modul295_lb1.productmanager.resources.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object für Benutzerdaten.
 * Verwendet zur Datenvalidierung in der UserController-Klasse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Benutzername darf nicht leer sein.")
    private String username;

    @NotBlank(message = "E-Mail darf nicht leer sein.")
    @Email(message = "E-Mail muss im richtigen Format sein.")
    private String email;

    @NotNull(message = "Aktivitätsstatus darf nicht null sein.")
    private String active;

}
