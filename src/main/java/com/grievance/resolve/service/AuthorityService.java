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
	private OtpService otpService;

	@Autowired
	private JwtService jwtService;

	public String login(AuthorityLoginDto loginDto) {
		Authority authority = authorityRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new RuntimeException("Authority not found"));

		if (!authority.getPassword().equals(loginDto.getPassword())) {
			throw new RuntimeException("Authority password is incorrect");
		}

		String otp = otpService.generateOtp(loginDto.getEmail());

		return "OTP Generated: " + otp;

	}

	public String otpVerification(String email, String otp) {
		boolean valid = otpService.validateOtp(email, otp);

		if (!valid) {
			throw new RuntimeException("Invalid OTP entered");
		}

		Authority authority = authorityRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Authority not found"));

		return jwtService.generateToken(

				authority.getEmail(), authority.getRole().name());

	}
}
