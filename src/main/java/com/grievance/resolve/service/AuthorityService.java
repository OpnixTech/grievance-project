package com.grievance.resolve.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grievance.resolve.dto.AuthorityLoginDto;
import com.grievance.resolve.dto.AuthorityUpdateDto;
import com.grievance.resolve.entity.Authority;
import com.grievance.resolve.entity.Grievance;
import com.grievance.resolve.repository.AuthorityRepository;
import com.grievance.resolve.repository.GrievanceRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private GrievanceRepository grievanceRepository;

	@Autowired
	private OtpService otpService;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private EmailService emailService;

	public String login(AuthorityLoginDto loginDto) {
		Authority authority = authorityRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new RuntimeException("Authority not found"));

		if (!authority.getPassword().equals(loginDto.getPassword())) {
			throw new RuntimeException("Authority password is incorrect");
		}

		String otp = otpService.generateOtp(loginDto.getEmail());
//		otpService.generateOtp(loginDto.getEmail());
		emailService.sendOtpEmail(loginDto.getEmail(), otp);

//		return "OTP Generated: " + otp;
		return "OTP sent to you on Email";

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
	
	public String updateGrievance(AuthorityUpdateDto dto, String email) {
		
		Authority authority=
				authorityRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Authority Not Found"));
		
		Grievance grievance=
				grievanceRepository.findByTicketNumber(dto.getTicketNumber()).orElseThrow(()-> new RuntimeException("Ticket not found"));
		
		if(!grievance.getAssignedAuthority()
				.getId()
				.equals(authority.getId())) {
			throw new RuntimeException("Not your grievance");
		}
		grievance.setStatus(dto.getStatus());
		grievance.setAuthorityRemark(dto.getAuthorityRemark());
		grievance.setUpdatedAt(LocalDateTime.now());
		grievanceRepository.save(grievance);
		return "Updated Successfully";
	}
}
