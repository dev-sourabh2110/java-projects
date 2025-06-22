package com.data.controller;

import com.data.dto.VendorProfileDTO;
import com.data.pojo.VendorRegistrationRequest;
import com.data.service.VendorService;
import com.data.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/public/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerVendor(@RequestBody VendorRegistrationRequest request) {
        try {
            Long vendorId = vendorService.registerVendor(
                    request.getFirstName(), request.getLastName(), request.getEmail(),
                    request.getPassword(), request.getPhoneNumber(), request.getAddress(),
                    request.getTradeLicenseNumber(), request.getDob());
        
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("vendorId", vendorId);
        return ApiResponse.success(responseData, "Vendor registered successfully. Awaiting admin approval.");
    } catch (Exception e) {
        return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
    }
}

    /**
     * Get vendor profile for the currently authenticated vendor.
     */
    @Secured("ROLE_VENDOR")
    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getVendorProfile(Authentication authentication) {
        try {
            String email = authentication.getName();
            VendorProfileDTO profile = vendorService.getVendorProfile(email);
            return ApiResponse.success(profile, "Vendor profile retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update vendor profile for the currently authenticated vendor.
     */
    @Secured("ROLE_VENDOR")
    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> updateVendorProfile(@RequestBody VendorProfileDTO profileDTO,
                                                              Authentication authentication) {
        try {
            String email = authentication.getName();
            VendorProfileDTO updatedProfile = vendorService.updateVendorProfile(email, profileDTO);
            return ApiResponse.success(updatedProfile, "Vendor profile updated successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }
}