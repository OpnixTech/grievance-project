package com.grievance.resolve.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grievance.resolve.dto.GrievanceDto;
import com.grievance.resolve.entity.Authority;
import com.grievance.resolve.entity.Grievance;
import com.grievance.resolve.entity.Status;
import com.grievance.resolve.mapper.GrievanceMapper;
import com.grievance.resolve.repository.AuthorityRepository;
import com.grievance.resolve.repository.GrievanceRepository;

@Service
public class GrievanceService {

	@Autowired
	private GrievanceRepository grievanceRepository;
	
	@Autowired
	private GrievanceMapper grievanceMapper;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	
	public GrievanceDto createGrievance(GrievanceDto grievanceDto) {
		Grievance grievance=grievanceMapper.toEntity(grievanceDto);
		grievance.setStatus(Status.PENDING);
		grievance.setTicketNumber(generateTicketNumber());
//		authorityRepository.findByDepartmentTypeAndStateAndCityAndDistrict(
//				grievance.getIssueType(),
//				grievance.getState(),
//				grievance.getDistrict(),
//				grievance.getCity()
//	).ifPresent(authority -> {
//		grievance.setAssignedAuthority(authority);
//		grievance.setStatus(Status.ASSIGNED);
//	});
		
		Authority nearestAuthority=findNearestAuthority(grievance);
		if(nearestAuthority !=null) {
			grievance.setAssignedAuthority(nearestAuthority);
			grievance.setStatus(Status.ASSIGNED);
		}
		
		Grievance saved=grievanceRepository.save(grievance);
		return grievanceMapper.toDto(saved);
	}
	
	public List<GrievanceDto> getUserGrievance(String username){
		List<Grievance> list=grievanceRepository.findByUsername(username);
		List<GrievanceDto> grievanceDtos=grievanceMapper.toList(list);
		return grievanceDtos;
	}
	
	private String generateTicketNumber() {
		String datePart=LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String randomPart=UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "TIC"+datePart+randomPart;
	}
	
	public GrievanceDto getGrievanceByTicket(String ticket) {
		Grievance grievance=grievanceRepository.findByTicketNumber(ticket).orElseThrow(()->new RuntimeException("Ticket Not Found"));
		return grievanceMapper.toDto(grievance);
	}
	
	private Double calculateDistance(double lat1, double long1, double lat2, double long2) {
		final int EART_RADIUS=6371;
		
		double latDistance=Math.toRadians(lat2-lat1);
		double longDistance=Math.toRadians(long2-long1);
		
		double a=Math.sin(latDistance/2)*Math.sin(latDistance/2)
				+Math.cos(Math.toRadians(lat1))
				*Math.cos(Math.toRadians(lat2))
				*Math.sin(longDistance/2)
				*Math.sin(longDistance/2);
		
		double c=2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return EART_RADIUS * c;
	}
	
	private Authority findNearestAuthority(Grievance grievance) {
		List<Authority> authorities=authorityRepository.findByDepartmentType(grievance.getDepartment());
		
		Authority nearest=null;
		double minDistance=Double.MAX_VALUE;
		
		for(Authority authority: authorities) {
			double distance=calculateDistance(grievance.getLatitude(), grievance.getLongitude(), authority.getLatitude(), authority.getLongitude());
			if(distance<minDistance) {
				minDistance=distance;
				nearest=authority;
			}
		}
		return nearest;
	}
	
}
