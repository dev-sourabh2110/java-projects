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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, 
                        VendorRepository vendorRepository, 
                        AdminRepository adminRepository,
                        AuthenticationManager authenticationManager,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.adminRepository = adminRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<LoginResponse> loginWithCredentials(String emailOrPhone, String password) {
        // Manually authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(emailOrPhone, password)
        );
        
        // Set authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Get authenticated user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Retrieve UserEntity from the database
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return buildLoginResponse(user);
    }
    
    public ResponseEntity<LoginResponse> loginResponse() {
        // Get authenticated user from Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    
        // Retrieve UserEntity from the database
        UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return buildLoginResponse(user);
    }
    
    private ResponseEntity<LoginResponse> buildLoginResponse(UserEntity user) {

        // Check if the user is a vendor and get vendorId
        Optional<VendorEntity> vendorOptional = vendorRepository.findByUserId(user.getId());
        Long vendorId = vendorOptional.map(VendorEntity::getId).orElse(null);

        // Check if the user is an admin and get adminId
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
