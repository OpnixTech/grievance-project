package com.grievance.resolve.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grievance.resolve.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	Optional<Authority> findByDepartmentTypeAndStateAndCityAndDistrict(
			String departmentType,
			String state,
			String district,
			String city
			);

}
