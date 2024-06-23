package com.vang.employeeservice.query.service.impl;

import com.vang.employeeservice.query.model.EmployeeResponseModel;
import com.vang.employeeservice.query.queries.GetAllEmployees;
import com.vang.employeeservice.query.queries.GetByEmployeeId;
import com.vang.employeeservice.query.service.EmployeeQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public EmployeeQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<EmployeeResponseModel> getEmployeeById(String id) {

        GetByEmployeeId employeeId = new GetByEmployeeId(id);
        EmployeeResponseModel responseModel = queryGateway.query(employeeId, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeeResponseModel>> getAllEmployee() {

        GetAllEmployees allEmployees = new GetAllEmployees();
        List<EmployeeResponseModel> responseModels = queryGateway.query(allEmployees, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }
}