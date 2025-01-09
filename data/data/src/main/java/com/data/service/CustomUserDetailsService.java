package com.data.service;

import com.data.entity.Role;
import com.data.entity.UserEntity;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from the database
        UserEntity userEntity = userRepository.findByEmailOrPhoneNumber(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Map the UserEntity to Spring Security User
        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword()) // Assuming password is already hashed
                .roles(userEntity.getRoles().stream()
                        .map(Role::getName)  // Get the role names from the Role entity
                        .toArray(String[]::new))  // Map roles to an array of Strings
                .build();
    }

}
