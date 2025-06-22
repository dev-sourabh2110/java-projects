package com.data.controller;

import com.data.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user/change-password")
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> changePassword(Authentication authentication, 
                                                @RequestParam String oldPassword, 
                                                @RequestParam String newPassword) {
        // Extract the email from the authenticated principal
        String email;
        if (authentication.getPrincipal() instanceof UserDetails) {
            email = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            email = authentication.getPrincipal().toString();
        }
        
        Map<String, Object> response = changePasswordService.changePassword(email, oldPassword, newPassword);
        
        // Return appropriate HTTP status based on success flag
        boolean isSuccess = (boolean) response.get("success");
        return ResponseEntity.status(isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(response);
    }
}