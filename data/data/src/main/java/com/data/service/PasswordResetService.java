package com.data.service;

import com.data.entity.UserEntity;
import com.data.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Generates a temporary password and sets an expiry timestamp (10 minutes from now).
     * Updates the user's password (hashed) and returns the temporary password.
     */
    public String generateTemporaryPassword(String emailOrPhone) {
        Optional<UserEntity> userOptional = userRepository.findByEmailOrPhoneNumber(emailOrPhone, emailOrPhone);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with provided email or phone");
        }
        UserEntity user = userOptional.get();
        // Generate a random temporary password of 10 characters
        String tempPassword = generateRandomPassword(10);
        // Hash the temporary password and update the user's password
        user.setPassword(passwordEncoder.encode(tempPassword));
        // Set temporary password expiry (10 minutes from now)
        Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000);
        user.setTempPasswordExpiry(expiryTime);
        userRepository.save(user);
        return tempPassword;
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
