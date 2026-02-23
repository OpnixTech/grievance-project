package com.grievance.resolve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grievance.resolve.dto.AuthorityLoginDto;
import com.grievance.resolve.entity.Authority;
import com.grievance.resolve.repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public String login(AuthorityLoginDto loginDto) {
		Authority authority=authorityRepository.findByEmail(loginDto.getEmail()).orElseThrow(()-> new RuntimeException("Authority not found"));
		
		if(!authority.getPassword().equals(loginDto.getPassword())) {
			throw new RuntimeException("Authority password is incorrect");
		}
		
		return jwtService.generateToken(
				
				authority.getEmail(),
				authority.getRole().name()
				);
		
	}

}
