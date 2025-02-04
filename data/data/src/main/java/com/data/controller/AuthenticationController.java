package com.data.controller;

import com.data.pojo.ForgetPasswordRequest;
import com.data.pojo.LoginRequest;
import com.data.service.LoginService;
import com.data.service.PasswordResetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request) {
        request.getHeader("User-Type");
      //  boolean isAuthenticated = loginService.authenticateUser(loginRequest);

      //  if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
       // }
       // return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody @Valid ForgetPasswordRequest forgetPasswordRequest) {
        String password = passwordResetService.getUserPassword(forgetPasswordRequest);
        if (password != null) {
            return ResponseEntity.ok("Your password is: " + password);
        }
        return ResponseEntity.status(404).body("User not found.");
    }
}

