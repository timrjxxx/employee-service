package com.employeeservice;

import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.service.EmployeeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);

	}

}
