package com.data.service;

import com.data.dto.VendorProfileDTO;
import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.entity.VendorEntity;
import com.data.pojo.response.VendorDTO;
import com.data.repository.CarRepository;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import com.data.repository.VendorRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {

    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final RoleRepository roleRepository;
    private final CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;

    public VendorService(UserRepository userRepository, VendorRepository vendorRepository,
                         RoleRepository roleRepository, CarRepository carRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.roleRepository = roleRepository;
        this.carRepository = carRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long registerVendor(String firstName, String lastName, String email, String password, String phoneNumber,
                               String address, String tradeLicenseNumber, Date dob) {
        try {
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

            user = userRepository.saveAndFlush(user);
            System.out.println("User saved: " + user.getId());
            // Create VendorEntity
            VendorEntity vendor = new VendorEntity();
            vendor.setUser(user);
            vendor.setTradeLicenseNumber(tradeLicenseNumber);
            vendor.setEnabled(false); // Requires admin approval

            vendor = vendorRepository.saveAndFlush(vendor);
            System.out.println(vendor.getId());
            return vendor.getId(); // Return the vendor ID
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error registering vendor: " + e.getMessage());
        }
    }

    /**
     * Fetch all vendors with pagination
     */
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> new VendorDTO(
                        vendor.getId(), vendor.getUser().getFirstName(), vendor.getUser().getLastName(),
                        vendor.getUser().getEmail(), vendor.getUser().getPhoneNumber(), vendor.isEnabled(),
                        vendor.getTradeLicenseNumber(), vendor.getCreateTime(), vendor.getUpdateTime()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Get a single vendor's details
     */
    public VendorDTO getVendorById(Long vendorId) {
        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        return new VendorDTO(
                vendor.getId(), vendor.getUser().getFirstName(), vendor.getUser().getLastName(),
                vendor.getUser().getEmail(), vendor.getUser().getPhoneNumber(), vendor.isEnabled(),
                vendor.getTradeLicenseNumber(), vendor.getCreateTime(), vendor.getUpdateTime()
        );
    }

    /**
     * Approve a vendor
     */
    @Transactional
    public void approveVendor(Long vendorId) {
        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        vendor.setEnabled(true);  // Enable vendor
        vendorRepository.save(vendor);
    }

    /**
     * Suspend a vendor
     */
    @Transactional
    public void suspendVendor(Long vendorId) {
        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        vendor.setEnabled(false);  // Disable vendor
        vendorRepository.save(vendor);
    }

    /**
     * Delete a vendor (Optional: Soft delete can be implemented if needed)
     */

    @Transactional
    public void deleteVendor(Long vendorId) {
        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        // Delete all cars associated with the vendor
        carRepository.deleteAllByVendor(vendor);

        // Delete vendor
        vendorRepository.delete(vendor);
    }

    /**
     * Get the vendor profile for the vendor with the given email.
     */
    public VendorProfileDTO getVendorProfile(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        VendorEntity vendor = vendorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Vendor not found for user id: " + user.getId()));

        return new VendorProfileDTO(
                vendor.getId(),
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getDob(),
                vendor.getTradeLicenseNumber(),
                vendor.isEnabled(),
                vendor.getCreateTime(),
                vendor.getUpdateTime()
        );
    }

    /**
     * Update the vendor profile for the vendor with the given email.
     * Only allowed fields are updated.
     */
    @Transactional
    public VendorProfileDTO updateVendorProfile(String email, VendorProfileDTO profileDTO) {
        // Find the user and vendor by email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        VendorEntity vendor = vendorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Vendor not found for user id: " + user.getId()));

        // Update user details
        user.setFirstName(profileDTO.firstName());
        user.setLastName(profileDTO.lastName());
        user.setPhoneNumber(profileDTO.phoneNumber());
        user.setAddress(profileDTO.address());
        user.setDob(profileDTO.dob());
        // Note: Email typically should not change.

        userRepository.save(user);

        // Update vendor-specific details
        vendor.setTradeLicenseNumber(profileDTO.tradeLicenseNumber());
        // You may update vendor's enabled status only via separate admin action.
        vendorRepository.save(vendor);

        return new VendorProfileDTO(
                vendor.getId(),
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getDob(),
                vendor.getTradeLicenseNumber(),
                vendor.isEnabled(),
                vendor.getCreateTime(),
                vendor.getUpdateTime()
        );
    }

}