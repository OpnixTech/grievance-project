package com.grievance.resolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grievance.resolve.dto.AuthorityLoginDto;
import com.grievance.resolve.service.AuthorityService;

@RestController
@RequestMapping("/api/authority")
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@PostMapping("/login")
	public String login(@RequestBody AuthorityLoginDto authorityLoginDto) {
		return authorityService.login(authorityLoginDto);
	}

}
