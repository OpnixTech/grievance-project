//package com.grievance.resolve.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfiguration {
//
//	 	@Autowired
//	    JwtFilter jwtFilter;
//
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(
//	            HttpSecurity http) throws Exception {
//
//	        return http
//
//	        .csrf(csrf->csrf.disable())
//
//	        .authorizeHttpRequests(auth->auth
//
//	        .requestMatchers(
//	        "/api/users/register",
//	        "/api/grievances/**",
//	        "/api/auth/login",
//	        "/api/authority/login",
//	        "/api/users/**",
//	        "/swagger-ui/**",
//	        "/api/authority/**",
//	        "/swagger-ui.html",
//	        "/v3/api-docs/**	"
//	        ).permitAll()
//
//	        .anyRequest().authenticated()
//
//	        )
//
//	        .addFilterBefore(
//	         jwtFilter,
//	         UsernamePasswordAuthenticationFilter.class
//	        )
//
//	        .build();
//	}
//	
//}
