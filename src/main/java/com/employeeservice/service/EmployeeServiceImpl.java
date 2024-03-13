package com.employeeservice.service;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import com.employeeservice.exception.EmployeeNotFoundException;
import com.employeeservice.mapper.EmployeeMapper;
import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper mapper = EmployeeMapper.INSTANCE;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void addEmployee(EmployeeRequestDTO employeeDTO, MultipartFile photo) throws IOException {
        Employee employee = mapper.reqToModel(employeeDTO);

        if (!photo.isEmpty()) {
            employee.setPhoto(photo.getBytes());
        }
        log.info("Edd employee with id: {}, name: {}, last name:{}, phone: {}, email:{}", employeeDTO.getEmployeeId(), employeeDTO.getFirstName(),
                employeeDTO.getLastName(), employeeDTO.getPhoneNumber(), employeeDTO.getEmail());

        log.debug("Employee's photo size: {}, bytes:{}, name: {}", photo.getSize(), photo.getBytes(), photo.getName());
        mapper.modelToRes(employeeRepository.save(employee));

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found"));
        log.info("Delete employee with id: {}", employeeId);
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateEmployee(MultipartFile photo, Long employeeId, EmployeeRequestDTO employeeDTO) throws IOException {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found"));
        log.info("Edit employee with id:{}", employeeId);
        mapper.updateEmployeeFromDto(employeeDTO, existingEmployee);
        if (!photo.isEmpty()) {
            existingEmployee.setPhoto(photo.getBytes());
        }
        log.info("Updated employee credentials: {}", employeeDTO);
        log.debug("Employee's photo size: {}, bytes:{}, name: {}", photo.getSize(), photo.getBytes(), photo.getName());
        mapper.modelToRes(employeeRepository.save(existingEmployee));
    }


    @Override
    public List<EmployeeResponseDTO> showAllEmployees() {
        log.info("Show all employees");
        return mapper.modelToRes(employeeRepository.findAll());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long employeeId) {
        log.info("Show employee profile ");
        log.info("Get employee by id: {}", employeeId);
        return mapper.modelToRes(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found")));
    }
}
