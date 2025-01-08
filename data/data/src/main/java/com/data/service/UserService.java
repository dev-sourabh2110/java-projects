package com.data.service;

import com.data.entity.UserEntity;
import com.data.pojo.User;
import com.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        // Check if password and confirm password match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match";
        }

        // Map POJO (User) to Entity (UserEntity)
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setDob(user.getDob());
        userEntity.setPassword(user.getPassword());  // Store password directly or hash it before saving

        // Save UserEntity to database
        userRepository.save(userEntity);
        return "User registered successfully";
    }
}
