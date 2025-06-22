package com.data.controller;

import com.data.entity.UserHistoryEntity;
import com.data.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/history")
public class UserHistoryController {
    @Autowired
    private UserHistoryService userHistoryService;

    // Fetch user history by type (appointment or purchase)
  //  @Secured("USER")
    @GetMapping("/{userEmail}")
    public ResponseEntity<List<UserHistoryEntity>> getHistory(
            @PathVariable String userEmail, @RequestParam String type) {
        return ResponseEntity.ok(userHistoryService.getUserHistoryByType(userEmail, type));
    }
}

