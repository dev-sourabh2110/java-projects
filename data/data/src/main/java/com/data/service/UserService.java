package com.data.service;

import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.pojo.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
        userEntity.setDob(user.getDob());
        userEntity.setPassword(encodedPassword);  // Store password directly or hash it before saving

        // Assign the USER role
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            // You could create a default role in the database if needed
            userRole = new Role("USER");
        }
        userEntity.getRoles().add(userRole);

        // Save UserEntity to database
        userRepository.save(userEntity);
        return "User registered successfully";
    }
}
