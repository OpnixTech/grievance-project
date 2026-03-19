
package com.grievance.resolve.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_registration")
public class UserRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private Long id;
	
	@NotBlank(message = "First name is required")
	private String firstName;
	private String middleName;
	
	@NotBlank(message = "Last name is required")
	private String lastName;
	
	@NotNull(message = "DOB name is required")
	private LocalDate dob;
	
	@NotBlank(message = "Phone number is mandatory")
	private long phone;
	
	@NotBlank(message = "Email is mandatory to proceed")
	private String email;
	
	private String address;
	
	@NotBlank(message = "Enter OTP sent on registered mail")
	private String otp;
	
	
	@Column(name="username", unique = true)
	private String username;
	
	@NotBlank(message = "Please enter password to proceed")
	private String password;
	private boolean verified;
	
	
	
	
	

}
