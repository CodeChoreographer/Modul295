package com.modul295_lb1.productmanager.resources.users;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) für die Anmeldeinformationen eines Benutzers.
 */
public class LoginRequest {

    @Schema(description = "Der Benutzername des Anmeldenden Benutzers", example = "Admin")
    private String username;

    @Schema(description = "Der Benutzername des Anmeldenden Benutzers", example = "Admin")
    private String email;

    @Schema(description = "Das Passwort des Anmeldenden Benutzers", example = "Admin")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsernameOrEmail(){
        if (username != null && !username.isEmpty()){
            return username;
        }else if (email != null && !email.isEmpty()){
            return email;
        }
        return null;
    }
}
