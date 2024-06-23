package com.vang.employeeservice.query.projection;

import com.vang.employeeservice.data.EmployeeRepository;
import com.vang.employeeservice.data.Employees;
import com.vang.employeeservice.query.model.EmployeeResponseModel;
import com.vang.employeeservice.query.queries.GetAllEmployees;
import com.vang.employeeservice.query.queries.GetByEmployeeId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeQueryProjection {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeQueryProjection(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @QueryHandler
    public List<EmployeeResponseModel> getAllEmployees(GetAllEmployees allEmployees) {

        List<Employees> listEmployees = employeeRepository.findAll();
        List<EmployeeResponseModel> employeeResponseModels = new ArrayList<>();
        listEmployees.forEach(e -> {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(e, employeeResponseModel);
            employeeResponseModels.add(employeeResponseModel);
        });
        return employeeResponseModels;
    }

    @QueryHandler
    public EmployeeResponseModel getByEmployeeId(GetByEmployeeId employeeId) {

        Employees employees = employeeRepository.findById(employeeId.getEmployeeId()).orElse(new Employees());
        EmployeeResponseModel responseModel = new EmployeeResponseModel();
        if(StringUtils.isEmpty(employees.getEmployeeId())) {

            responseModel.initialize();
        }else {

            BeanUtils.copyProperties(employees, responseModel);
        }
        return responseModel;
    }
}