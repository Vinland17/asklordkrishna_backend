package com.asklordkrishna.backend.security;

import com.asklordkrishna.backend.service.UserService;
import com.asklordkrishna.backend.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        logger.info("Processing request: " + requestURI);

        // Get Authorization header
        final String authorizationHeader = request.getHeader("Authorization");
        logger.info("Authorization header: " + (authorizationHeader != null ? "Present" : "Missing"));

        String email = null;
        String jwt = null;

        // Check if Authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
            logger.info("JWT token extracted: " + jwt.substring(0, Math.min(jwt.length(), 20)) + "...");

            try {
                email = jwtUtil.getEmailFromToken(jwt);
                logger.info("Email extracted from token: " + email);
            } catch (Exception e) {
                logger.warn("Unable to get email from JWT token: " + e.getMessage());
            }
        }

        // If email is extracted and no authentication is set in context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Find user by email
            Optional<User> userOptional = userService.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                logger.info("User found: " + user.getEmail());

                // Validate token
                if (jwtUtil.validateToken(jwt, email)) {
                    logger.info("Token is valid, setting authentication");

                    // Create authentication token
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    new ArrayList<>() // No roles for simplicity
                            );

                    // Set authentication details
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set authentication in security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Authentication set successfully");
                } else {
                    logger.warn("Token validation failed");
                }
            } else {
                logger.warn("User not found for email: " + email);
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        // Skip JWT validation for public endpoints
        return path.equals("/api/auth/login") ||
                path.equals("/api/auth/register") ||
                path.equals("/api/auth/health");
    }
}
