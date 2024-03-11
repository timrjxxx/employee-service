package com.employeeservice.controller;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import com.employeeservice.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employee")
public class ThymeleafEmployeeController {

    private final EmployeeServiceImpl service;

    @Autowired
    public ThymeleafEmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listEmployees(Model model) {
        List<EmployeeResponseDTO> employees = service.showAllEmployees();

        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/details/{id}")
    public String viewEmployeeDetails(@PathVariable Long id, Model model) {
        EmployeeResponseDTO employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/details";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeRequestDTO());
        return "employee/add";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute EmployeeRequestDTO dto) {
        // Логика добавления сотрудника
        service.addEmployee(dto);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EmployeeResponseDTO employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, @ModelAttribute EmployeeRequestDTO dto) {
        service.updateEmployee(id, dto);
        return "redirect:/employee/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "redirect:/employee/list";
    }
}
