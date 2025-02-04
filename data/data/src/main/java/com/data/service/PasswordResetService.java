package com.data.service;

import com.data.entity.UserEntity;
import com.data.pojo.ForgetPasswordRequest;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    public String getUserPassword(ForgetPasswordRequest forgetPasswordRequest) {
        String emailOrPhone = forgetPasswordRequest.getEmailOrPhone();
        UserEntity user = userRepository.findByEmailOrPhoneNumber(emailOrPhone, emailOrPhone).orElse(null);;

        if (user != null) {
            return user.getPassword();
        }
        return null;
    }
}
