package com.data.controller;

import com.data.pojo.ForgetPasswordRequest;
import com.data.pojo.LoginRequest;
import com.data.service.LoginService;
import com.data.service.PasswordResetService;
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
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.authenticateUser(loginRequest);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody @Valid ForgetPasswordRequest forgetPasswordRequest) {
        boolean isReset = passwordResetService.resetPassword(forgetPasswordRequest);

        if (isReset) {
            return ResponseEntity.ok("Password reset link sent successfully");
        }
        return ResponseEntity.status(404).body("User not found");
    }
}

