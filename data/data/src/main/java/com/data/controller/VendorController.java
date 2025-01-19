package com.data.controller;

import com.data.pojo.VendorRegistrationRequest;
import com.data.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerVendor(@RequestBody VendorRegistrationRequest request) {
        vendorService.registerVendor(
                request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPassword(), request.getPhoneNumber(), request.getAddress(),
                request.getTradeLicenseNumber(), request.getDob());
        return ResponseEntity.ok("Vendor registered successfully. Awaiting admin approval.");
    }
}
