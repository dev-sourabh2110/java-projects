package com.data.controller;

import com.data.service.CommissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/commission")
public class AdminCommissionController {

    private final CommissionService commissionService;

    public AdminCommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    /**
     * Set commission for a category
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/commission")
    public ResponseEntity<String> setCommission(
            @RequestParam String category,
            @RequestParam Double value,
            @RequestParam boolean isPercentage) {
        commissionService.setCommission(category, value, isPercentage);
        return ResponseEntity.ok("Commission set successfully");
    }
}
