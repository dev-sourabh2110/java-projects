//package com.data.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Base64;
//
//@Order(2)
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
////    private final AuthenticationManager authenticationManager;
//
//    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
////        this.authenticationManager = authenticationManager;
//        super.setAuthenticationManager(authenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//        String authorizationHeader = request.getHeader("Authorization");
//        String userType = request.getHeader("User-Type");
//
//        if (authorizationHeader == null || userType == null) {
//            throw new RuntimeException("Missing Authorization or User-Type header");
//        }
//
//        request.setAttribute("UserType", userType);
//        // Decode the Basic Auth credentials
//        String base64Credentials = authorizationHeader.replace("Basic ", "");
//        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
//        String[] parts = credentials.split(":");
//        String username = parts[0];
//        String password = parts[1];
//
//        // Return a CustomAuthenticationToken
//        CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(username, password, userType);
//        return this.getAuthenticationManager().authenticate(authenticationToken);
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//}
//
