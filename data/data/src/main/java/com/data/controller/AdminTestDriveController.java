package com.data.controller;

import com.data.dto.TestDriveDTO;
import com.data.service.TestDriveService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/test-drives")
public class AdminTestDriveController {

    private final TestDriveService testDriveService;

    public AdminTestDriveController(TestDriveService testDriveService) {
        this.testDriveService = testDriveService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<TestDriveDTO>> getTestDrives(@RequestParam String status) {
        return ResponseEntity.ok(testDriveService.getTestDrivesByStatus(status));
    }
}
