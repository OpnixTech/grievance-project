package com.grievance.resolve.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grievance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String title;
	private String description;
	private Double lattitude;
	private Double Longitude;
	private String department;
	private String ticketNumber;
	private String state;
	private String district;
	private String city;
	private String issueType;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="authority_id")
	private Authority assignedAuthority;
	
}
