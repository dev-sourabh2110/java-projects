package com.data.service;

import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.entity.AdminEntity;
import com.data.entity.VendorEntity;
import com.data.pojo.response.VendorResponse;
import com.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final VendorRepository vendorRepository;
    private final CarRepository carRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, AdminRepository adminRepository,
                        RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder, CarRepository carRepository, VendorRepository vendorRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.carRepository = carRepository;
        this.vendorRepository = vendorRepository;
    }

    public void registerAdmin(String firstName, String lastName, String email, String password, String phoneNumber,
                              String address, boolean active, Date dob) {
        // Create UserEntity
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setDob(dob);

        // Assign ADMIN role
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
        user.setRoles(Collections.singleton(adminRole));

        user = userRepository.save(user);

        // Create AdminEntity
        AdminEntity admin = new AdminEntity();
        admin.setUser(user);
        admin.setActive(active);

        adminRepository.save(admin);
    }

    public Map<String, Object> getDashboardMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalCustomers", userRepository.count());
        metrics.put("totalVendors", vendorRepository.count());
        metrics.put("totalCarsListed", carRepository.count());
        metrics.put("totalRevenue", carRepository.sumCarRevenue());
        return metrics;
    }

    public List<VendorResponse> getAllVendors() {
        List<VendorEntity> vendors = vendorRepository.findAll();

        return vendors.stream().map(vendor -> {
            UserEntity user = vendor.getUser();
            VendorResponse response = new VendorResponse();
            response.setId(vendor.getId());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setAddress(user.getAddress());
            response.setTradeLicenseNumber(vendor.getTradeLicenseNumber());
            response.setEnabled(vendor.isEnabled());
            response.setDob(user.getDob());
            return response;
        }).collect(Collectors.toList());
    }
}
