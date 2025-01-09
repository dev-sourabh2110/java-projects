package com.data.controller;

import com.data.pojo.TestDriveRequest;
import com.data.service.TestDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/test-drive")
public class TestDriveController {

    @Autowired
    private TestDriveService testDriveService;

    // POST endpoint to submit test drive request
//    @Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/request")
    public ResponseEntity<String> requestTestDrive(@RequestBody @Valid TestDriveRequest testDriveRequest, BindingResult result) {
        String responseMessage = testDriveService.saveTestDriveRequest(testDriveRequest, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(responseMessage);
        }

        return ResponseEntity.ok(responseMessage);
    }
}
