package com.data.service;

import com.data.entity.UserEntity;
import com.data.pojo.User;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserProfile(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String updateUserProfile(String email, User updatedUser) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        userEntity.setFirstName(updatedUser.getFirstName());
        userEntity.setLastName(updatedUser.getLastName());
        userEntity.setPhoneNumber(updatedUser.getPhoneNumber());
        userEntity.setDob(updatedUser.getDob());

        // Save updated profile
        userRepository.save(userEntity);
        return "Profile updated successfully!";
    }
}
