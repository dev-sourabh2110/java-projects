package com.data.controller;

import com.data.entity.UserEntity;
import com.data.pojo.User;
import com.data.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> getProfile(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(userProfileService.getUserProfile(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@AuthenticationPrincipal UserDetails user, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(user.getUsername(), updatedUser));
    }
}
