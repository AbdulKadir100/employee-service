package com.employeeservice.employee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.employeeservice.employee.Dto.OrganizationDto;


//spring cloud feign client internally uses Load Balancer to avoid API down

@FeignClient(name="ORGANIZATION-SERVICE")
public interface ApiClientOrganization {
		
		// Build get by code
		@GetMapping("api/organizations/{organization-code}")
		OrganizationDto getOrganization(@PathVariable("organization-code") String code);
}
