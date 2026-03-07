package com.grievance.resolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.grievance.resolve.dto.AuthorityLoginDto;
import com.grievance.resolve.dto.AuthorityUpdateDto;
import com.grievance.resolve.dto.OtpVerificationDto;
import com.grievance.resolve.service.AuthorityService;

@RestController
@RequestMapping("/api/authority")
@CrossOrigin(origins = "*")
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@PostMapping("/login")
	public String login(@RequestBody AuthorityLoginDto authorityLoginDto) {
		return authorityService.login(authorityLoginDto);
	}
	
	@PostMapping("/login/verify-otp")
	public String verifyLogin(@RequestBody OtpVerificationDto otpVerificationDto) {
		return authorityService.otpVerification(otpVerificationDto.getEmail(), otpVerificationDto.getOtp());
	}
	
	@PutMapping("/update")
	public String updateGrievance(@RequestBody AuthorityUpdateDto authorityUpdateDto, @RequestParam String email) {
		return authorityService.updateGrievance(authorityUpdateDto, email);
	}

}
