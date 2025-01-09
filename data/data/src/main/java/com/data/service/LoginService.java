package com.data.service;

import com.data.entity.UserEntity;
import com.data.pojo.LoginRequest;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(LoginRequest loginRequest) {
        String emailOrPhone = loginRequest.getEmailOrPhone();
        Optional<UserEntity> user = userRepository.findByEmailOrPhoneNumber(emailOrPhone, emailOrPhone);

        if (user.isPresent()) {
            // Assuming password is stored securely in hashed format, compare with hashed value
            return user.get().getPassword().equals(loginRequest.getPassword());
        }
        return false;
    }
}