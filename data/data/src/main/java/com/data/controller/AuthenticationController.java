package com.data.controller;

import com.data.dto.ForgotPasswordRequest;
import com.data.dto.ForgotPasswordResponse;
import com.data.dto.LoginResponse;
import com.data.pojo.ForgetPasswordRequest;
import com.data.service.LoginService;
import com.data.service.PasswordResetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request) {
       // request.getHeader("User-Type");
        return loginService.loginResponse();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        String temporaryPassword = passwordResetService.generateTemporaryPassword(request.emailOrPhone());
        ForgotPasswordResponse response = new ForgotPasswordResponse(
                temporaryPassword,
                "Temporary password is valid for 10 minutes. Please change your password after login."
        );
        return ResponseEntity.ok(response);
    }
}

