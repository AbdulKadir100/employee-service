package com.employeeservice.employee.service;

import com.employeeservice.employee.Dto.APIResponseDto;
import com.employeeservice.employee.Dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	APIResponseDto getEmployee(Long id);

}
