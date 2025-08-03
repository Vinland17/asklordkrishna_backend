package com.asklordkrishna.backend.service;

import com.asklordkrishna.backend.model.User;
import com.asklordkrishna.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register a new user
    public User registerUser(String username, String email, String password) {
        // Check if user already exists
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        // Create new user with encrypted password
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Verify user password
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // Check if email exists
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Check if username exists
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Get user by ID
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    // Update user password
    public User updatePassword(String email, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    // Delete user by email
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    // Get total user count
    public long getTotalUsers() {
        return userRepository.count();
    }
}
