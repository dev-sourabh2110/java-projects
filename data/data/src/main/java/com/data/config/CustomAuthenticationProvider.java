//package com.data.config;
//
//import com.data.service.AdminDetailsService;
//import com.data.service.CustomUserDetailsService;
//import com.data.service.VendorDetailsService;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final CustomUserDetailsService userDetailsService;
//    private final VendorDetailsService vendorDetailsService;
//    private final AdminDetailsService adminDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService,
//                                        VendorDetailsService vendorDetailsService,
//                                        AdminDetailsService adminDetailsService,
//                                        PasswordEncoder passwordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.vendorDetailsService = vendorDetailsService;
//        this.adminDetailsService = adminDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if (!(authentication instanceof CustomAuthenticationToken)) {
//          //  throw new AuthenticationServiceException("Unsupported authentication token type");
//           // return authentication;
//        }
//
//
//        CustomAuthenticationToken customToken = (CustomAuthenticationToken) authentication;
//        String username = customToken.getPrincipal().toString();
//        String password = customToken.getCredentials().toString();
//        String userType = customToken.getUserType();
//
//        // Route to the appropriate UserDetailsService
//        UserDetails userDetails;
//        switch (userType.toUpperCase()) {
//            case "VENDOR":
//                userDetails = vendorDetailsService.loadUserByUsername(username);
//                break;
//            case "ADMIN":
//                userDetails = adminDetailsService.loadUserByUsername(username);
//                break;
//            case "USER":
//                userDetails = userDetailsService.loadUserByUsername(username);
//                break;
//            default:
//                throw new BadCredentialsException("Invalid user type: " + userType);
//        }
//
//        // Validate the password
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            throw new BadCredentialsException("Invalid credentials");
//        }
//
//        return new CustomAuthenticationToken(userDetails, password, userDetails.getAuthorities(), userType);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return CustomAuthenticationToken.class.isAssignableFrom(authentication) ||
//                UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//
//
////    @Override
////    public boolean supports(Class<?> auth) {
////        return auth.equals(UsernamePasswordAuthenticationToken.class);
////    }
//}
