package com.vang.employeeservice.command.service;

import com.vang.employeeservice.command.model.EmployeeRequestModel;
import com.vang.employeeservice.command.model.UpdateEmployeeRequestModel;
import com.vang.employeeservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface EmployeeCommandService {

    ResponseEntity<ResponseCommon> addEmployee(EmployeeRequestModel requestModel);

    ResponseEntity<ResponseCommon> updateEmployee(UpdateEmployeeRequestModel requestModel);

    ResponseEntity<ResponseCommon> deleteEmployee(EmployeeRequestModel requestModel);
}