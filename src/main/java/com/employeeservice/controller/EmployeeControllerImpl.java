package com.employeeservice.controller;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import com.employeeservice.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeServiceImpl service;

    @Autowired
    public EmployeeControllerImpl(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    @Override
    public String listEmployees(Model model) {
        List<EmployeeResponseDTO> employees = service.showAllEmployees();

        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/details/{id}")
    @Override
    public String viewEmployeeDetails(@PathVariable Long id, Model model) {
        EmployeeResponseDTO employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/details";
    }

    @GetMapping("/add")
    @Override
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeRequestDTO());
        return "employee/add";
    }

    @PostMapping("/add")
    @Override
    public String addEmployee(@RequestParam("file") MultipartFile photo, @ModelAttribute EmployeeRequestDTO dto) throws IOException {
        service.addEmployee(dto, photo);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    @Override
    public String showEditForm(@PathVariable Long id, Model model) {
        EmployeeResponseDTO employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    @Override
    public String editEmployee(@PathVariable Long id, @ModelAttribute EmployeeRequestDTO dto) {
        service.updateEmployee(id, dto);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    @Override
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "redirect:/employee";
    }
}
