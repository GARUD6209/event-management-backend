package com.harsh.eventmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/events/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure security with method chaining and lambda-based configuration
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITE_LIST).permitAll() // Allow public access to /events
                        .anyRequest().authenticated()  // Authenticate other requests
                );

        return http.build();  // Build the SecurityFilterChain
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // Configure in-memory authentication (for testing purposes)
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);  // Return the user details service
//    }
}