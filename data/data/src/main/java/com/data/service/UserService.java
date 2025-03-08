package com.data.service;

import com.data.dto.UserDTO;
import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.pojo.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        // Check if password and confirm password match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match";
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // Map POJO (User) to Entity (UserEntity)
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setAddress(user.getAddress());
        userEntity.setDob(user.getDob());
        userEntity.setPassword(encodedPassword);  // Store password directly or hash it before saving

        // Assign the USER role
        Optional<Role> userRoleObj = roleRepository.findByName("USER");
        Role userRole = userRoleObj.orElseThrow(() -> new IllegalArgumentException("USER role not found."));

        if (userRole == null) {
            // You could create a default role in the database if needed
            userRole = new Role();
        }
        userEntity.getRoles().add(userRole);

        // Save UserEntity to database
        UserEntity save = userRepository.save(userEntity);
        return "User registered successfully";
    }

    public UserEntity getUserProfile(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    public void updateUserProfile(String userEmail, UserEntity updatedProfile) {
        UserEntity existingUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        existingUser.setFirstName(updatedProfile.getFirstName());
        existingUser.setLastName(updatedProfile.getLastName());
        existingUser.setPhoneNumber(updatedProfile.getPhoneNumber());
        existingUser.setDob(updatedProfile.getDob());
        userRepository.save(existingUser);
    }

    public void changeUserPassword(String userEmail, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        if (!new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect old password.");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    /**
     * Fetch all users
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(), user.getFirstName(), user.getLastName(),
                        user.getEmail(), user.getPhoneNumber(), user.isEnabled(),
                        user.getCreateTime(), user.getUpdateTime()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Get a single user details
     */
    public UserDTO getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        return new UserDTO(
                user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPhoneNumber(), user.isEnabled(),
                user.getCreateTime(), user.getUpdateTime()
        );
    }

    /**
     * Suspend a user
     */
    @Transactional
    public void suspendUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setEnabled(false);  // Disable user
        userRepository.save(user);
    }

    /**
     * Delete a user
     */
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
