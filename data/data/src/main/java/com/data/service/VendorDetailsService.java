//package com.data.service;
//
//import com.data.entity.VendorEntity;
//import com.data.repository.VendorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VendorDetailsService implements UserDetailsService {
//
//    @Autowired
//    private VendorRepository vendorRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        VendorEntity vendor = vendorRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Vendor not found: " + username));
//
//        return User.builder()
//                .username(vendor.getEmail())
//                .password(vendor.getPassword())
//                .roles("VENDOR") // Assign VENDOR role
//                .build();
//    }
//}
//
