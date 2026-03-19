package com.grievance.resolve.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationDto {
	
//	private String firstName;
//	private String middleName;
//	private String lastName;
//	private LocalDate dob;
//	private long phone;
//	private String email;
//	private String address;
//	private String password;
	
	@NotBlank(message = "First name is required")
	private String firstName;
	private String middleName;
	
	@NotBlank(message = "Last name is required")
	private String lastName;
	
	@NotNull(message = "DOB name is required")
	private LocalDate dob;
	
	@NotBlank(message = "Phone number is mandatory")
	private String phone;
	
	@NotBlank(message = "Email is mandatory to proceed")
	private String email;
	
	private String address;
	
//	@NotBlank(message = "Enter OTP sent on registered mail")
//	private String otp;
	
	
//	@Column(name="username", unique = true)
//	private String username;
	
	@NotBlank(message = "Please enter password to proceed")
	private String password;

}
