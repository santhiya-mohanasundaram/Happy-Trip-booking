package com.tripease.travelbooking.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    // Can be username, email, or phone number
    private String identifier;
    private String email;
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    public AuthRequest() {}

    public AuthRequest(String identifier, String password) {
        this.identifier = identifier;
        this.username = identifier;
        this.email = identifier;
        this.password = password;
    }

    public String getIdentifier() {
        if (identifier != null && !identifier.isBlank()) return identifier.trim();
        if (username != null && !username.isBlank()) return username.trim();
        if (email != null && !email.isBlank()) return email.trim();
        return "";
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
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
}
