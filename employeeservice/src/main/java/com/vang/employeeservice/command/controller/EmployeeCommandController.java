package com.vang.employeeservice.command.controller;

import com.vang.employeeservice.command.model.EmployeeRequestModel;
import com.vang.employeeservice.command.model.UpdateEmployeeRequestModel;
import com.vang.employeeservice.command.service.EmployeeCommandService;
import com.vang.employeeservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees/")
public class EmployeeCommandController {

    private final EmployeeCommandService employeeCommandService;

    @Autowired
    public EmployeeCommandController(EmployeeCommandService employeeCommandService) {
        this.employeeCommandService = employeeCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addEmployee(@RequestBody EmployeeRequestModel requestModel) {

        return employeeCommandService.addEmployee(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updateEmployee(@ModelAttribute UpdateEmployeeRequestModel requestModel) {

        return employeeCommandService.updateEmployee(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deleteEmployee(@RequestBody EmployeeRequestModel requestModel) {
        return employeeCommandService.deleteEmployee(requestModel);
    }
}
