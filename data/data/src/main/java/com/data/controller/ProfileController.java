package com.data.controller;

import com.data.entity.UserEntity;
import com.data.entity.UserHistoryEntity;
import com.data.entity.WishlistEntity;
import com.data.service.UserHistoryService;
import com.data.service.UserService;
import com.data.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    // Fetch profile details
    @Secured("USER")
    @GetMapping("/{userEmail}")
    public ResponseEntity<UserEntity> getProfile(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.getUserProfile(userEmail));
    }

    // Update profile details
    @Secured("USER")
    @PutMapping("/{userEmail}")
    public ResponseEntity<String> updateProfile(@PathVariable String userEmail, @RequestBody UserEntity updatedProfile) {
        userService.updateUserProfile(userEmail, updatedProfile);
        return ResponseEntity.ok("Profile updated successfully.");
    }

    // Change password
    @Secured("USER")
    @PutMapping("/{userEmail}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable String userEmail, @RequestBody

              Map<String, String> passwords) {
        userService.changeUserPassword(userEmail, passwords.get("oldPassword"), passwords.get("newPassword"));
        return ResponseEntity.ok("Password changed successfully.");
    }
}

