package com.vang.employeeservice.query.controller;

import com.vang.employeeservice.query.model.EmployeeResponseModel;
import com.vang.employeeservice.query.service.EmployeeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees/")
public class EmployeeQueryController {

    private final EmployeeQueryService employeeQueryService;

    @Autowired
    public EmployeeQueryController(EmployeeQueryService employeeQueryService) {
        this.employeeQueryService = employeeQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseModel> getEmployeeById(@PathVariable("id") String id) {

        return employeeQueryService.getEmployeeById(id);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseModel>> getAllEmployees() {

        return employeeQueryService.getAllEmployee();
    }
}