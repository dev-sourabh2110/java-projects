package com.data.controller;

import com.data.pojo.AdminRegistrationRequest;
import com.data.pojo.response.VendorResponse;
import com.data.service.AdminApprovalService;
import com.data.service.AdminService;
import com.data.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminApprovalService adminApprovalService;

    @Autowired
    private CommissionService commissionService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationRequest request) {
        adminService.registerAdmin(
                request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword(), request.getPhoneNumber(), request.getAddress(),
                request.isActive(), request.getDob());
        return ResponseEntity.ok("Admin registered successfully.");
    }

    @Secured("ADMIN")
    @GetMapping("/dashboard/metrics")
    public ResponseEntity<Map<String, Object>> getMetrics() {
        return ResponseEntity.ok(adminService.getDashboardMetrics());
    }

    @Secured("ADMIN")
    @PostMapping("/approvals/vendor/{vendorId}")
    public ResponseEntity<String> approveVendor(@PathVariable Long vendorId) {
        String message = adminApprovalService.approveVendor(vendorId);
        return ResponseEntity.ok(message);
    }

    @Secured("ADMIN")
    @PostMapping("/approvals/car/{carId}")
    public ResponseEntity<String> approveCar(@PathVariable Long carId) {
        String message = adminApprovalService.approveCar(carId);
        return ResponseEntity.ok(message);
    }

    @Secured("ADMIN")
    @PostMapping("/commission")
    public ResponseEntity<String> setCommission(
            @RequestParam String category, @RequestParam Double value, @RequestParam boolean isPercentage) {
        String message = commissionService.setCommission(category, value, isPercentage);
        return ResponseEntity.ok(message);
    }

    @Secured("ADMIN")
    @GetMapping("/dashboard/vendors")
    public ResponseEntity<List<VendorResponse>> getAllVendors() {
        List<VendorResponse> vendors = adminService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }


}

