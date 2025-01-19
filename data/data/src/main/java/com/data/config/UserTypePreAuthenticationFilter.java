//package com.data.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class UserTypePreAuthenticationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String userType = request.getHeader("User-Type"); // Header to identify user type
//
//        if (userType == null) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing User-Type header");
//            return;
//        }
//
//        // Add user type to request context for later use
//        request.setAttribute("UserType", userType);
//
//        chain.doFilter(request, response);
//    }
//}
//
