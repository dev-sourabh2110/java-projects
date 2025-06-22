package com.data.controller;

import com.data.pojo.User;
import com.data.service.UserService;
import com.data.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ApiResponse.badRequest("Invalid input data");
        }

        try {
            String message = userService.registerUser(user);
            return ApiResponse.success(message);
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }
}