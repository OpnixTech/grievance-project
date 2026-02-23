package com.grievance.resolve.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

	 	@Autowired
	    JwtFilter jwtFilter;

	    @Bean
	    public SecurityFilterChain securityFilterChain(
	            HttpSecurity http) throws Exception {

	        return http

	        .csrf(csrf->csrf.disable())

	        .authorizeHttpRequests(auth->auth

	        .requestMatchers(
	        "/api/users/register",
	        "/user/login",
	        "/authority/register",
	        "/api/authority/login",
	        "/api/users/**",
	        "/swagger-ui/**"
	        ).permitAll()

	        .anyRequest().authenticated()

	        )

	        .addFilterBefore(
	         jwtFilter,
	         UsernamePasswordAuthenticationFilter.class
	        )

	        .build();
	}
	
}
