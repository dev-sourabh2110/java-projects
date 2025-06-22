package com.data.controller;

import com.data.dto.AdminReportDTO;
import com.data.pojo.AdminRegistrationRequest;
import com.data.pojo.response.VendorResponse;
import com.data.service.AdminApprovalService;
import com.data.service.AdminService;
import com.data.service.CommissionService;
import com.data.service.ReportService;
import com.data.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ReportService reportService;
    private final AdminApprovalService adminApprovalService;
    private final CommissionService commissionService;
    private final AdminService adminService;

    @Autowired
    public AdminController(ReportService reportService, AdminApprovalService adminApprovalService, CommissionService commissionService, AdminService adminService) {
        this.reportService = reportService;
        this.adminApprovalService = adminApprovalService;
        this.commissionService = commissionService;
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationRequest request) {
        adminService.registerAdmin(
                request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword(), request.getPhoneNumber(), request.getAddress(),
                request.isActive(), request.getDob());
        return ResponseEntity.ok("Admin registered successfully.");
    }

   // @Secured("ADMIN")
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

    //@PreAuthorize("ROLE_ADMIN")
    @GetMapping("/reports")
    public ResponseEntity<Map<String, Object>> getReports() {
        try {
            AdminReportDTO reports = reportService.getReports();
            return ApiResponse.success(reports, "Reports retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    //@Secured("ADMIN")
    @GetMapping("/dashboard/vendors")
    public ResponseEntity<List<VendorResponse>> getAllVendors() {
        List<VendorResponse> vendors = adminService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }


}

