package com.data.config;

import com.data.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 Console
                                .requestMatchers("/api/auth/login", "/api/auth/forget-password", "/api/public/register").permitAll()  // Allow login, register, and forget-password without authentication
                                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER", "ADMIN")  // Only 'USER' and 'ADMIN' can access GET endpoints
                                .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")  // Only 'ADMIN' can POST to user endpoints
//                                .requestMatchers(HttpMethod.POST, "/api/test-drive/request/**").hasAnyRole("ADMIN", "USER")  // Only 'ADMIN' can POST to user endpoints
                                .anyRequest().authenticated()  // Require authentication for other requests
                )            .httpBasic(httpBasic -> {})  // Apply HTTP Basic Authentication using the new Customizer

//                .formLogin(form ->
//                        form
//                                .loginPage("/login")  // Custom login page URL
//                                .loginProcessingUrl("/login-process")  // URL for handling form submissions
//                                .defaultSuccessUrl("/home", true)  // Redirect to this URL on successful login
//                                .failureUrl("/login?error=true")  // Redirect on failed login
//                                .permitAll()  // Allow access to the login page without authentication
//                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")  // Disable CSRF protection for H2 console
                )
                .headers(headers ->
                        headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)  // Allow iframe for H2 console by setting the frame-ancestors same-origin policy
                )
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF protection for the rest of the application (stateless APIs)
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt password encoder for securing passwords
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return http.getSharedObject(AuthenticationManager.class);
    }
}
