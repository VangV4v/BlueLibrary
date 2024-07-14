package com.vang.apigateway.security;

import com.vang.apigateway.common.ServiceCommon;
import com.vang.apigateway.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] noRole = {"/api/v1/auth/employee/", "/api/v1/auth/user/", "/api/v1/auth/admin/"};
        String[] roleEmployee = {"/api/v1/types/", "/api/v1/publishers/", "/api/v1/authors/", "/api/v1/users/", "/api/v1/books/"};
        String[] roleUser = {"/api/v1/users/"};
        String[] roleAdmin = {"/api/v1/admin/", "/api/v1/users/", "/api/v1/employees/"};
        http.csrf(CsrfConfigurer::disable);
        http.cors(config ->
                config.configurationSource(corsConfigurationSource())
                );
        http.authorizeHttpRequests(auth -> {

            auth.requestMatchers(noRole).permitAll()
                    .requestMatchers(roleEmployee).hasAnyRole(ServiceCommon.ROLE_EMPLOYEE, ServiceCommon.ROLE_ADMIN)
                    .requestMatchers(roleAdmin).hasRole(ServiceCommon.ROLE_ADMIN)
                    .anyRequest().denyAll();
        });
        http.addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}