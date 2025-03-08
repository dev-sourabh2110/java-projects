package com.data.service;

import com.data.dto.LoginResponse;
import com.data.entity.AdminEntity;
import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.entity.VendorEntity;
import com.data.pojo.LoginRequest;
import com.data.repository.AdminRepository;
import com.data.repository.UserRepository;
import com.data.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final AdminRepository adminRepository;


    public LoginService(UserRepository userRepository, VendorRepository vendorRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.adminRepository = adminRepository;
    }


    public ResponseEntity<LoginResponse> loginResponse() {
        // Get authenticated user from Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Retrieve UserEntity from the database
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user is a vendor and get vendorId
        Optional<VendorEntity> vendorOptional = vendorRepository.findByUserId(user.getId());
        Long vendorId = vendorOptional.map(VendorEntity::getId).orElse(null);

        // Check if the user is a vendor and get vendorId
        Optional<AdminEntity> adminOptional = adminRepository.findByUserId(user.getId());
        Long adminId = adminOptional.map(AdminEntity::getId).orElse(null);

        // Get roles as a set of strings
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        // Build response object
        LoginResponse response = new LoginResponse(user.getId(), vendorId, adminId, user.getEmail(), roles);

        // Return JSON response with Authorization header
        return ResponseEntity.ok()
                .body(response);
    }
}
