package com.grievance.resolve.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Department {
	
	PWD,
	ELECTRICITY,
	WATER,
	ROAD,
	PUBLIC_WORKS;
	
//	@JsonCreator
//	public static Department from(String value) {
//		return Department.valueOf(value.toUpperCase());
//	}

}
