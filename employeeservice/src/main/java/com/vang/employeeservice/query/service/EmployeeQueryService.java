package com.vang.employeeservice.query.service;

import com.vang.employeeservice.query.model.EmployeeResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeQueryService {

    ResponseEntity<EmployeeResponseModel> getEmployeeById(String id);

    ResponseEntity<List<EmployeeResponseModel>> getAllEmployee();

}