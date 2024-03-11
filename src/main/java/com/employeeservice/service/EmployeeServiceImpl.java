package com.employeeservice.service;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import com.employeeservice.exception.EmployeeNotFoundException;
import com.employeeservice.mapper.EmployeeMapper;
import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper mapper = EmployeeMapper.INSTANCE;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeDTO) {

        return mapper.modelToRes(employeeRepository.save(mapper.reqToModel(employeeDTO)));

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found"));
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long employeeId, EmployeeRequestDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found"));

        mapper.updateEmployeeFromDto(employeeDTO, existingEmployee);
        return mapper.modelToRes(employeeRepository.save(existingEmployee));
    }


    @Override
    public List<EmployeeResponseDTO> showAllEmployees() {
        return mapper.modelToRes(employeeRepository.findAll());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long employeeId) {
        return mapper.modelToRes(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found")));
    }
}
