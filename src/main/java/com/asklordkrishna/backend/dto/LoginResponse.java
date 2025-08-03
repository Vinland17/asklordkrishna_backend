package com.asklordkrishna.backend.dto;

public class LoginResponse {

    private String token;
    private String username;
    private String email;
    private String message;

    // Default constructor
    public LoginResponse() {}

    // Constructor for successful login
    public LoginResponse(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.message = "Login successful";
    }

    // Constructor for error response
    public LoginResponse(String message) {
        this.message = message;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + (token != null ? "[TOKEN_PRESENT]" : "null") + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
