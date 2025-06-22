package com.data.service;

import com.data.entity.UserEntity;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChangePasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, Object> changePassword(String email, String oldPassword, String newPassword) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validate input parameters
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }

            if (oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
                throw new IllegalArgumentException("Passwords cannot be empty");
            }

            // Find user with better error message
            UserEntity userEntity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

            if (!passwordEncoder.matches(oldPassword, userEntity.getPassword())) {
                response.put("success", false);
                response.put("message", "Current password is incorrect");
                return response;
            }

            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
            
            response.put("success", true);
            response.put("message", "Password changed successfully");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            response.put("error", e.getClass().getSimpleName());
        }
        
        return response;
    }
}