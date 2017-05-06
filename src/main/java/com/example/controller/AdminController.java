package com.example.controller;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final EmployeeRepository employeeRepo;

    public AdminController(EmployeeRepository employeeRepository) {
        this.employeeRepo = employeeRepository;
    }

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeRepo.findAll();
    }
}
