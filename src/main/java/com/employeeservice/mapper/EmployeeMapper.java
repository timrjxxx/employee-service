package com.employeeservice.mapper;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import com.employeeservice.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee reqToModel(EmployeeRequestDTO dto);

    EmployeeResponseDTO modelToRes(Employee employee);

    List<EmployeeResponseDTO> modelToRes(List<Employee> employees);

    @Mapping(target = "employeeId", ignore = true)
    void updateEmployeeFromDto(EmployeeRequestDTO dto, @MappingTarget Employee employee);
}
