package com.example.controller;

import com.example.entity.Employee;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/me")
    @JsonView(Employee.View.Public.class)
    public Employee getMe(@AuthenticationPrincipal Employee employee) {
        return employee;
    }
}