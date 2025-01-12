package com.data.controller;

import com.data.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/change-password")
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @PostMapping
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return ResponseEntity.ok(changePasswordService.changePassword(email, oldPassword, newPassword));
    }
}

