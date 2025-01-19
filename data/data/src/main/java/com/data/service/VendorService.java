package com.data.service;

import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.entity.VendorEntity;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import com.data.repository.VendorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class VendorService {

    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public VendorService(UserRepository userRepository, VendorRepository vendorRepository,
                         RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerVendor(String firstName, String lastName, String email, String password, String phoneNumber,
                               String address, String tradeLicenseNumber, Date dob) {
        // Create UserEntity
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setDob(dob);

        // Assign VENDOR role
        Role vendorRole = roleRepository.findByName("VENDOR")
                .orElseThrow(() -> new RuntimeException("Role VENDOR not found"));
        user.setRoles(Collections.singleton(vendorRole));

        user = userRepository.save(user);

        // Create VendorEntity
        VendorEntity vendor = new VendorEntity();
        vendor.setUser(user);
        vendor.setTradeLicenseNumber(tradeLicenseNumber);
        vendor.setEnabled(false); // Requires admin approval

        vendorRepository.save(vendor);
    }
}
