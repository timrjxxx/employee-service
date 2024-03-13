package com.employeeservice.service;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    void addEmployee(EmployeeRequestDTO employeeDTO, MultipartFile photo) throws IOException;

    void deleteEmployee(Long employeeId);

    void updateEmployee(MultipartFile photo,Long id, EmployeeRequestDTO requestDTO)throws IOException;

    List<EmployeeResponseDTO> showAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long Id);
}
