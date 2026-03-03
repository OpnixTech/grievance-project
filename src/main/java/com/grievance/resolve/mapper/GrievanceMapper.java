package com.grievance.resolve.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.grievance.resolve.dto.GrievanceDto;
import com.grievance.resolve.entity.Department;
import com.grievance.resolve.entity.Grievance;

@Mapper(componentModel = "spring")
public interface GrievanceMapper {
	
	Grievance toEntity(GrievanceDto dto);
	
	GrievanceDto toDto(Grievance grievance);
	
//	default Department map(String value) {
//		return value==null?null:Department.valueOf(value.toUpperCase());
//	}
//	
	List<GrievanceDto> toList(List<Grievance> grievances);

}
