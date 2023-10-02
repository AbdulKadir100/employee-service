package com.employeeservice.employee.service.Imp;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeservice.employee.Dto.APIResponseDto;
import com.employeeservice.employee.Dto.DepartmentDto;
import com.employeeservice.employee.Dto.EmployeeDto;
import com.employeeservice.employee.entity.Employee;
import com.employeeservice.employee.repository.EmployeeRepository;
import com.employeeservice.employee.service.APIClient;
import com.employeeservice.employee.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService{
	private EmployeeRepository employeeRepository;
	private ModelMapper mapper;
	private APIClient apiClient;
	//private WebClient webClient;
	//private RestTemplate restTemplate;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = mapper.map(employeeDto, Employee.class);
		Employee savedEmployee =  employeeRepository.save(employee);
		return mapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	public APIResponseDto getEmployee(Long id){
		Employee employee = employeeRepository.findById(id).get();

	//************ Microservices communication using RestTemplate   *************//
//		ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(),
//				DepartmentDto.class);
		
		//extract the department entity from response entity
//		DepartmentDto departmentDto = responseEntity.getBody();
		

		//************ Microservices communication using WebClient *************//
//		 DepartmentDto departmentDto =   webClient.get()
//		.uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
//		.retrieve()
//		.bodyToMono(DepartmentDto.class)
//		.block();
		
		
		
		//************ Microservices communication using OpenFeign(Spring Cloud) *************//
		DepartmentDto departmentDto= apiClient.getDepartment(employee.getDepartmentCode());
		
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		
		APIResponseDto responseDto = new APIResponseDto();
		responseDto.setEmployee(employeeDto);
		responseDto.setDepartment(departmentDto);
		
		
		return responseDto;
	}

}
