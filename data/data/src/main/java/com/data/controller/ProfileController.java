package com.data.controller;

import com.data.entity.UserEntity;
import com.data.service.UserService;
import com.data.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    // Fetch profile details
    //@Secured("USER")
    @GetMapping("/{userEmail}")
    public ResponseEntity<UserEntity> getProfile(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.getUserProfile(userEmail));
    }

    // Update profile details
    //@Secured("USER")
    @PutMapping("/{userEmail}")
    public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable String userEmail, @RequestBody UserEntity updatedProfile) {
        try {
            userService.updateUserProfile(userEmail, updatedProfile);
            return ApiResponse.success("Profile updated successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    // Change password
    @Secured({"ROLE_USER","ROLE_VENDOR","ROLE_ADMIN"})
    @PutMapping("/{userEmail}/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(@PathVariable String userEmail, @RequestBody Map<String, String> passwords) {
        try {
            userService.changeUserPassword(userEmail, passwords.get("oldPassword"), passwords.get("newPassword"));
            return ApiResponse.success("Password changed successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }
}