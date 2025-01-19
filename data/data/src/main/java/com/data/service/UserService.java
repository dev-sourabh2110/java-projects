package com.data.service;

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

import javax.swing.text.html.Option;
import java.util.Optional;


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
        userRepository.save(userEntity);
        return "User registered successfully";
    }

    public UserEntity getUserProfile(String userEmail) {
        return userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    public void updateUserProfile(String userEmail, UserEntity updatedProfile) {
        UserEntity existingUser = userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        existingUser.setFirstName(updatedProfile.getFirstName());
        existingUser.setLastName(updatedProfile.getLastName());
        existingUser.setPhoneNumber(updatedProfile.getPhoneNumber());
        existingUser.setDob(updatedProfile.getDob());
        userRepository.save(existingUser);
    }

    public void changeUserPassword(String userEmail, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        if (!new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect old password.");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }
}
