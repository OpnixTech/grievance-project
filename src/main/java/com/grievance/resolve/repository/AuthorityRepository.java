package com.grievance.resolve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grievance.resolve.entity.Authority;
import com.grievance.resolve.entity.Department;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	List<Authority> findByDepartmentTypeAndStateAndCityAndDistrict(
			Department departmentType,
			String state,
			String district,
			String city
			);
	
	Optional<Authority> findByEmail(String email);
	
	List<Authority> findByDepartmentType(Department departmentType);

}
