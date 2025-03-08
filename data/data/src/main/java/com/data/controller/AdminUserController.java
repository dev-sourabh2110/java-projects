package com.data.controller;

import com.data.dto.UserDTO;
import com.data.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Get user by ID
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    /**
     * Suspend a user
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/{userId}/suspend")
    public ResponseEntity<String> suspendUser(@PathVariable Long userId) {
        userService.suspendUser(userId);
        return ResponseEntity.ok("User suspended successfully");
    }

    /**
     * Delete a user
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
