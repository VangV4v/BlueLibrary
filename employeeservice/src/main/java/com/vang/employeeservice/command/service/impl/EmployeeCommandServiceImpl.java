package com.vang.employeeservice.command.service.impl;

import com.vang.employeeservice.command.command.CreateEmployeeCommand;
import com.vang.employeeservice.command.command.DeleteEmployeeCommand;
import com.vang.employeeservice.command.command.UpdateEmployeeCommand;
import com.vang.employeeservice.command.model.EmployeeRequestModel;
import com.vang.employeeservice.command.model.UpdateEmployeeRequestModel;
import com.vang.employeeservice.command.service.EmployeeCommandService;
import com.vang.employeeservice.common.ResponseCommon;
import com.vang.employeeservice.common.ServiceCommon;
import com.vang.employeeservice.data.EmployeeRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final CommandGateway commandGateway;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeCommandServiceImpl(CommandGateway commandGateway, EmployeeRepository employeeRepository) {
        this.commandGateway = commandGateway;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<ResponseCommon> addEmployee(EmployeeRequestModel requestModel) {

        long checkUsername = employeeRepository.countByUsername(requestModel.getUsername());
        long checkEmail = employeeRepository.countByEmail(requestModel.getEmail());
        long checkPhone = employeeRepository.countByPhone(requestModel.getPhone());
        Set<String> errList = new HashSet<>();
        if(checkUsername > 0) {
            errList.add(ServiceCommon.ERROR_001);
        }
        if(checkEmail > 0) {
            errList.add(ServiceCommon.ERROR_002);
        }
        if(checkPhone > 0) {
            errList.add(ServiceCommon.ERROR_003);
        }
        if(!errList.isEmpty()) {
            ResponseCommon response = ResponseCommon.builder().error(true).errorMessage(errList).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CreateEmployeeCommand command = new CreateEmployeeCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        command.setCreatedDate(ServiceCommon.getFullCurrentDate());
        command.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        command.setActiveStatus(ServiceCommon.ONE);
        command.setRole(ServiceCommon.ROLE_EMPLOYEE);
        command.setAvatar(ServiceCommon.DEFAULT_IMAGE);
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.ADD_EMPLOYEE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updateEmployee(UpdateEmployeeRequestModel requestModel) {

        UpdateEmployeeCommand command = new UpdateEmployeeCommand();
        long checkExistEmail = employeeRepository.countByEmailAndOld(requestModel.getEmail(), requestModel.getHdnEmail());
        long checkExistPhone = employeeRepository.countByPhoneAndOld(requestModel.getPhone(), requestModel.getHdnPhone());
        Set<String> errList = new HashSet<>();
        if(checkExistEmail > 0) {
            errList.add(ServiceCommon.ERROR_002);
        }
        if(checkExistPhone > 0) {
            errList.add(ServiceCommon.ERROR_003);
        }
        if(!errList.isEmpty()) {
            ResponseCommon response = ResponseCommon.builder().error(true).errorMessage(errList).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(requestModel, command);
        if(!ObjectUtils.isEmpty(requestModel.getImage())) {

            try {
                command.setImageName(requestModel.getImage().getOriginalFilename());
                command.setImage(requestModel.getImage().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        command.setLastModified(ServiceCommon.getFullCurrentDate());
        command.setPassword(checkIsEncodePassword(requestModel.getPassword()));
        command.setRole(ServiceCommon.ROLE_EMPLOYEE);
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.UPDATE_EMPLOYEE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deleteEmployee(EmployeeRequestModel requestModel) {

        DeleteEmployeeCommand command = new DeleteEmployeeCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.DELETE_EMPLOYEE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String checkIsEncodePassword(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(password.contains("$") && password.startsWith("$")) {
            return password;
        }
        return passwordEncoder.encode(password);
    }
}