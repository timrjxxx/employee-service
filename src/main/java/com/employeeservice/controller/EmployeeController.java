package com.employeeservice.controller;

import com.employeeservice.dto.EmployeeRequestDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmployeeController {


    String listEmployees(Model model);

    String viewEmployeeDetails(Long id, Model model);


    String showAddForm(Model model);

    String addEmployee(MultipartFile photo, EmployeeRequestDTO dto) throws IOException;

    String showEditForm(Long id, Model model);


    String editEmployee(MultipartFile photo, Long id, EmployeeRequestDTO dto) throws IOException;

    String deleteEmployee(Long id);

}


