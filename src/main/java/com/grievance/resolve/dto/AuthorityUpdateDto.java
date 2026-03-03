package com.grievance.resolve.dto;

import com.grievance.resolve.entity.Status;

import lombok.Data;

@Data
public class AuthorityUpdateDto {
	
	private String ticketNumber;
	private Status status;
	private String authorityRemark;

}
