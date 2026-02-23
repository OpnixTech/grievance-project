package com.grievance.resolve.configuration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.grievance.resolve.service.JwtService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtService jwtService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
			) throws ServletException, IOException{
		
		String header=request.getHeader("AUTHORIZATION");
		
		if(header!=null && header.startsWith("Bearer")){
			String token=header.substring(7);
			String username=jwtService.extractUsername(token);
			
			UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			}
		try {
			filterChain.doFilter(request, response);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		}
	}

