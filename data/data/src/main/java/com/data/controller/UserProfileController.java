package com.data.controller;

import com.data.entity.UserEntity;
import com.data.pojo.User;
import com.data.service.UserProfileService;
import com.data.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile(Authentication authentication) {
        try {
            String email;
            if (authentication.getPrincipal() instanceof UserDetails) {
                email = ((UserDetails) authentication.getPrincipal()).getUsername();
            } else {
                email = authentication.getPrincipal().toString();
            }

            UserEntity profile = userProfileService.getUserProfile(email);
            return ApiResponse.success(profile, "Profile retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> updateProfile(Authentication authentication, @RequestBody User updatedUser) {
        try {
            String email;
            if (authentication.getPrincipal() instanceof UserDetails) {
                email = ((UserDetails) authentication.getPrincipal()).getUsername();
            } else {
                email = authentication.getPrincipal().toString();
            }

            String result = userProfileService.updateUserProfile(email, updatedUser);
            return ApiResponse.success(result);
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }
}
