package com.data.controller;

import com.data.pojo.response.VendorDTO;
import com.data.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/vendors")
public class AdminVendorController {

    private final VendorService vendorService;

    public AdminVendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Get all vendors
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
            return ResponseEntity.ok(vendorService.getAllVendors());
    }

    /**
     * Get vendor by ID
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorService.getVendorById(vendorId));
    }

    /**
     * Approve a vendor
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/{vendorId}/approve")
    public ResponseEntity<String> approveVendor(@PathVariable Long vendorId) {
        vendorService.approveVendor(vendorId);
        return ResponseEntity.ok("Vendor approved successfully");
    }

    /**
     * Suspend a vendor
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/{vendorId}/suspend")
    public ResponseEntity<String> suspendVendor(@PathVariable Long vendorId) {
        vendorService.suspendVendor(vendorId);
        return ResponseEntity.ok("Vendor suspended successfully");
    }

    /**
     * Delete a vendor
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{vendorId}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long vendorId) {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.ok("Vendor deleted successfully");
    }
}
