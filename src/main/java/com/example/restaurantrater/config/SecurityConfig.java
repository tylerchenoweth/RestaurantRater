package com.example.restaurantrater.config;

import com.example.restaurantrater.entities.Reviewer;
import com.example.restaurantrater.services.ReviewerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Password encoder bean using BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * UserDetailsService bean that loads user-specific data.
     */
    @Bean
    public UserDetailsService userDetailsService(ReviewerService reviewerService) {
        return username -> {
            Reviewer reviewer = reviewerService.findByUsername(username);
            if (reviewer == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User.builder()
                    .username(reviewer.getUsername())
                    .password(reviewer.getPassword())
                    .roles(reviewer.isAdmin() ? "ADMIN" : "USER")
                    .build();
        };
    }

    /**
     * Authentication provider bean that uses the UserDetailsService and PasswordEncoder.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    /**
     * Security filter chain for the H2 console.
     * This filter chain has higher precedence (@Order(1)) and allows unrestricted access.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            // Apply security rules only to /h2-console/** URLs
            .securityMatcher("/h2-console/**")
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            // Disable CSRF protection for H2 console
            .csrf(csrf -> csrf.disable())
            // Allow frames from the same origin to enable H2 console UI
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                    .sameOrigin()
                )
            );

        return http.build();
    }

    /**
     * Main security filter chain for the rest of the application.
     * This filter chain has lower precedence (@Order(2)) and enforces authentication.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DaoAuthenticationProvider authProvider) throws Exception {
        http
            // Set the custom authentication provider
            .authenticationProvider(authProvider)
            .authorizeHttpRequests(authorize -> authorize
                // Permit all access to registration, static resources, and H2 console is handled separately
                .requestMatchers("/register", "/css/**", "/js/**").permitAll()
                // Require authentication for any other requests
                .anyRequest().authenticated()
            )
            // Configure form-based login
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            // Configure logout
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
