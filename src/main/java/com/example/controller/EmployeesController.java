package com.example.controller;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeRepository repo;

    public EmployeesController(EmployeeRepository employeeRepository) {
        this.repo = employeeRepository;
    }

    @GetMapping("")
    @JsonView(Employee.View.Public.class)
    public Iterable<Employee> getEmployees() {
        return repo.findAll();
    }
}
