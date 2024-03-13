package com.employeeservice.controller;

import com.employeeservice.dto.EmployeeRequestDTO;
import com.employeeservice.dto.EmployeeResponseDTO;
import com.employeeservice.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeServiceImpl service;

    @Autowired
    public EmployeeControllerImpl(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    @Override
    public String listEmployees(Model model) {
        log.info("Request to list employees");
        List<EmployeeResponseDTO> employees = service.showAllEmployees();
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/details/{id}")
    @Override
    public String viewEmployeeDetails(@PathVariable Long id, Model model) {
        log.info("Request to view details of employee with ID: {}", id);
        EmployeeResponseDTO employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);

        String photoBase64 = Base64.encodeBase64String(employee.getPhoto());
        model.addAttribute("photoBase64", photoBase64);

        return "employee/details";
    }

    @GetMapping("/add")
    @Override
    public String showAddForm(Model model) {
        log.info("Request to show add employee form");
        model.addAttribute("employee", new EmployeeRequestDTO());
        return "employee/add";
    }

    @PostMapping("/add")
    @Override
    public String addEmployee(@RequestParam("file") MultipartFile photo, @ModelAttribute EmployeeRequestDTO dto) throws IOException {
        log.info("Request to add new employee");
        log.debug("Photo size: {}, bytes: {}, name: {}", photo.getSize(), photo.getBytes(),photo.getName());
        service.addEmployee(dto, photo);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    @Override
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("Request to show edit employee form for employee with ID: {}", id);
        EmployeeResponseDTO employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    @Override
    public String editEmployee(@RequestParam("file") MultipartFile photo, @PathVariable Long id, @ModelAttribute EmployeeRequestDTO dto) throws IOException {
        log.info("Request to edit employee with ID: {}", id);
        log.debug("Photo size: {}, bytes: {}, name: {}", photo.getSize(), photo.getBytes(),photo.getName());

        service.updateEmployee(photo, id, dto);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    @Override
    public String deleteEmployee(@PathVariable Long id) {
        log.info("Request to delete employee with ID: {}", id);
        service.deleteEmployee(id);
        return "redirect:/employee";
    }

    @GetMapping("/signin")
    public String showSignInForm() {
        log.info("Request to show sign-in form");
        return "employee/signin";
    }

    @GetMapping("/signup")
    public String showSignUpForm() {
        log.info("Request to show sign-up form");
        return "employee/signup";
    }
}
