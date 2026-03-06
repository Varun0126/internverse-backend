package com.internverse.internverse.service;

import com.internverse.internverse.config.JwtUtil;
import com.internverse.internverse.model.User;
import com.internverse.internverse.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRole().equalsIgnoreCase("admin")) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_INTERN");
        }

        return repository.save(user);
    }

    public Map<String, Object> login(String email, String password) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(password, user.getPassword())) {

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getId());
            response.put("role", user.getRole());
            response.put("name", user.getName()); // ✅ needed for MyCertificates

            return response;
        }

        throw new RuntimeException("Invalid Password");
    }
}
