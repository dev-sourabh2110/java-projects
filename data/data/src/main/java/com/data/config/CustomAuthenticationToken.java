//package com.data.config;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
//    private final String userType;
//
//    public CustomAuthenticationToken(String principal, String credentials, String userType) {
//        super(principal, credentials);
//        this.userType = userType;
//    }
//
//    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String userType) {
//        super(principal, credentials,authorities);
//        this.userType = userType;
//    }
//
//    public String getUserType() {
//        return userType;
//    }
//}
