package com.employeeservice.employee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employeeservice.employee.Dto.DepartmentDto;

// spring cloud feign client internally uses Load Balancer to avoid API down
@FeignClient(name="DEPARTMENT-SERVICE") 
public interface APIClient {
	
	// Build get by code
	@GetMapping("api/departments/{department-code}")
	DepartmentDto getDepartment(@PathVariable("department-code") String code);

}
