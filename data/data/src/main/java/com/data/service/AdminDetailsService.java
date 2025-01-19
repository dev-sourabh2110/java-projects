//package com.data.service;
//
//import com.data.entity.AdminEntity;
//import com.data.repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AdminEntity admin = adminRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));
//
//        return User.builder()
//                .username(admin.getEmail())
//                .password(admin.getPassword())
//                .roles("ADMIN") // Assign ADMIN role
//                .build();
//    }
//}
//
