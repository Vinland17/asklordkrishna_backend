package com.asklordkrishna.backend.repository;

import com.asklordkrishna.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find user by email (for login)
    Optional<User> findByEmail(String email);

    // Find user by username
    Optional<User> findByUsername(String username);

    // Check if email already exists (for registration validation)
    boolean existsByEmail(String email);

    // Check if username already exists (for registration validation)
    boolean existsByUsername(String username);

    // Delete user by email (if needed for admin functions)
    void deleteByEmail(String email);
}
