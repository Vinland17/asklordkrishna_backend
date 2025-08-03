package com.asklordkrishna.backend.service;

import com.asklordkrishna.backend.dto.LoginRequest;
import com.asklordkrishna.backend.dto.LoginResponse;
import com.asklordkrishna.backend.dto.RegisterRequest;
import com.asklordkrishna.backend.model.User;
import com.asklordkrishna.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Handle user registration
    public LoginResponse register(RegisterRequest registerRequest) {
        try {
            // Validate input
            if (registerRequest.getUsername() == null || registerRequest.getUsername().trim().isEmpty()) {
                return new LoginResponse("Username is required");
            }

            if (registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()) {
                return new LoginResponse("Email is required");
            }

            if (registerRequest.getPassword() == null || registerRequest.getPassword().length() < 6) {
                return new LoginResponse("Password must be at least 6 characters long");
            }

            // Check if user already exists
            if (userService.existsByEmail(registerRequest.getEmail())) {
                return new LoginResponse("Email already registered");
            }

            if (userService.existsByUsername(registerRequest.getUsername())) {
                return new LoginResponse("Username already taken");
            }

            // Register new user
            User user = userService.registerUser(
                    registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    registerRequest.getPassword()
            );

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail());

            // Return successful response
            return new LoginResponse(token, user.getUsername(), user.getEmail());

        } catch (Exception e) {
            return new LoginResponse("Registration failed: " + e.getMessage());
        }
    }

    // Handle user login
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Validate input
            if (loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
                return new LoginResponse("Email is required");
            }

            if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
                return new LoginResponse("Password is required");
            }

            // Find user by email
            Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());

            if (userOptional.isEmpty()) {
                return new LoginResponse("Invalid email or password");
            }

            User user = userOptional.get();

            // Verify password
            if (!userService.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
                return new LoginResponse("Invalid email or password");
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail());

            // Return successful response
            return new LoginResponse(token, user.getUsername(), user.getEmail());

        } catch (Exception e) {
            return new LoginResponse("Login failed: " + e.getMessage());
        }
    }

    // Validate JWT token
    public boolean validateToken(String token, String email) {
        try {
            return jwtUtil.validateToken(token, email);
        } catch (Exception e) {
            return false;
        }
    }

    // Get email from JWT token
    public String getEmailFromToken(String token) {
        try {
            return jwtUtil.getEmailFromToken(token);
        } catch (Exception e) {
            return null;
        }
    }

    // Check if token is valid
    public boolean isTokenValid(String token) {
        try {
            return jwtUtil.isTokenValid(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Get user details from token
    public User getUserFromToken(String token) {
        try {
            String email = jwtUtil.getEmailFromToken(token);
            if (email != null) {
                Optional<User> userOptional = userService.findByEmail(email);
                return userOptional.orElse(null);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
