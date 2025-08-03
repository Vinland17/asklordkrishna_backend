package com.asklordkrishna.backend.controller;

import com.asklordkrishna.backend.dto.LoginRequest;
import com.asklordkrishna.backend.dto.LoginResponse;
import com.asklordkrishna.backend.dto.RegisterRequest;
import com.asklordkrishna.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    // User registration endpoint
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            LoginResponse response = authService.register(registerRequest);

            // Check if registration was successful (token will be present)
            if (response.getToken() != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse("Registration failed: " + e.getMessage()));
        }
    }

    // User login endpoint
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.login(loginRequest);

            // Check if login was successful (token will be present)
            if (response.getToken() != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse("Login failed: " + e.getMessage()));
        }
    }

    // Token validation endpoint (updated with better error handling)
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Authorization header missing or invalid format. Use: Bearer <token>");
            }

            String token = authHeader.substring(7);

            if (authService.isTokenValid(token)) {
                String email = authService.getEmailFromToken(token);
                return ResponseEntity.ok("Token is valid for user: " + email);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Token validation failed: " + e.getMessage());
        }
    }

    // Health check endpoint (public access)
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Ask Lord Krishna Backend is running! 🕉️");
    }

    // Logout endpoint (optional - mainly for clearing frontend state)
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Since we're using JWT tokens, logout is handled on the frontend
        // by removing the token from storage
        return ResponseEntity.ok("Logout successful");
    }
}
