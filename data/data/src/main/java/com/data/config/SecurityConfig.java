package com.data.config;

//import com.data.service.AdminDetailsService;
import com.data.service.CustomUserDetailsService;
//import com.data.service.VendorDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${app.security.enabled}") // Read the property
    private boolean securityEnabled;

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (securityEnabled) {
            // 🔒 Security ENABLED: Enforce authentication/authorization
            configureSecurityEnabled(http);
        } else {
            // 🔓 Security DISABLED: Permit all requests
            configureSecurityDisabled(http);
        }
        return http.build();
    }

    private void configureSecurityEnabled(HttpSecurity http) throws Exception {
        http
            .anonymous(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers(
                        "/api/auth/login",
                        "/api/auth/forgot-password",
                        "/api/auth/forget-password",
                        "/api/public/register",
                        "/api/vendors/register",
                        "/api/admin/register",
                            "/api/cars/**"
                    ).permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/wishlist/**").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/api/profile/**").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/api/wishlist/**").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/api/vendors/**").hasRole("VENDOR")
                 //   .requestMatchers(HttpMethod.POST, "/api/cars/**").hasRole("VENDOR")
                  //  .requestMatchers(HttpMethod.GET, "/api/cars/**").hasRole("VENDOR")
                   // .requestMatchers(HttpMethod.PUT, "/api/cars/**").hasRole("VENDOR")
                    .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
//                    .requestMatchers(HttpMethod.GET, "/api/cars/**").hasAnyRole("USER", "VENDOR")
                    .requestMatchers(HttpMethod.POST, "/api/cars/**").hasAnyRole("USER", "VENDOR")
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
            );
    }

    private void configureSecurityDisabled(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow all requests
            )
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
            );
    }

    // Keep existing beans (PasswordEncoder, AuthenticationManager, etc.)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Secure passwords with BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

