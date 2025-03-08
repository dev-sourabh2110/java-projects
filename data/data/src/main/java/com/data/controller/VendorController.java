package com.data.controller;

import com.data.dto.VendorProfileDTO;
import com.data.pojo.VendorRegistrationRequest;
import com.data.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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

    /**
     * Get vendor profile for the currently authenticated vendor.
     */
    @Secured("ROLE_VENDOR")
    @GetMapping("/profile")
    public ResponseEntity<VendorProfileDTO> getVendorProfile(Authentication authentication) {
        String email = authentication.getName();
        VendorProfileDTO profile = vendorService.getVendorProfile(email);
        return ResponseEntity.ok(profile);
    }

    /**
     * Update vendor profile for the currently authenticated vendor.
     */
    @Secured("ROLE_VENDOR")
    @PutMapping("/profile")
    public ResponseEntity<VendorProfileDTO> updateVendorProfile(@RequestBody VendorProfileDTO profileDTO,
                                                                Authentication authentication) {
        String email = authentication.getName();
        VendorProfileDTO updatedProfile = vendorService.updateVendorProfile(email, profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
