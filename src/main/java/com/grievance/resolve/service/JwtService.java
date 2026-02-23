package com.grievance.resolve.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey";

	public String generateToken(String username, String role) {

		Map<String, Object> claims = new HashMap<>();

		claims.put("role", role);

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).compact();
	}

	public String extractUsername(String token) {

		return extractAllClaims(token).getSubject();
	}

	public String extractRole(String token) {

		return extractAllClaims(token).get("role", String.class);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody();
	}

}