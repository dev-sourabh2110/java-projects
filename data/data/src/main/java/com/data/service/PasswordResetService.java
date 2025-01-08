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

    public boolean resetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        String emailOrPhone = forgetPasswordRequest.getEmailOrPhone();
        Optional<UserEntity> user = userRepository.findByEmailOrPhoneNumber(emailOrPhone, emailOrPhone);

        if (user.isPresent()) {
            // Here we could send a reset token or email with password reset instructions
            // For simplicity, let's just return true (in a real app, generate and send a token/email)
            return true;
        }
        return false;
    }
}
