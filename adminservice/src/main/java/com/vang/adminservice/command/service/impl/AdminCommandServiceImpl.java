package com.vang.adminservice.command.service.impl;

import com.vang.adminservice.command.command.CreateAdminCommand;
import com.vang.adminservice.command.command.DeleteAdminCommand;
import com.vang.adminservice.command.command.UpdateAdminCommand;
import com.vang.adminservice.command.model.AdminRequestModel;
import com.vang.adminservice.command.model.UpdateAdminRequestModel;
import com.vang.adminservice.command.service.AdminCommandService;
import com.vang.adminservice.common.ResponseCommon;
import com.vang.adminservice.common.ServiceCommon;
import com.vang.adminservice.data.AdminRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class AdminCommandServiceImpl implements AdminCommandService {

    private final CommandGateway commandGateway;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminCommandServiceImpl(CommandGateway commandGateway, AdminRepository adminRepository) {
        this.commandGateway = commandGateway;
        this.adminRepository = adminRepository;
    }

    @Override
    public ResponseEntity<ResponseCommon> addAdmin(AdminRequestModel requestModel) {

        ResponseCommon response = null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CreateAdminCommand command = new CreateAdminCommand();
        Set<String> errs = new HashSet<>();
        long checkExistUsername = adminRepository.getCountByUsername(requestModel.getUsername());
        long checkExistEmail = adminRepository.getCountByEmail(requestModel.getEmail());
        long checkExistPhone = adminRepository.getCountByPhone(requestModel.getPhone());

        if (checkExistUsername > 0) {

            errs.add(ServiceCommon.ERROR_001);
        }
        if (checkExistEmail > 0) {

            errs.add(ServiceCommon.ERROR_002);
        }
        if (checkExistPhone > 0) {

            errs.add(ServiceCommon.ERROR_003);
        }
        if (!errs.isEmpty()) {

            response = ResponseCommon.builder().error(Boolean.TRUE).errorMessage(errs).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        command.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        command.setRole(ServiceCommon.ROLE_ADMIN);
        command.setCreatedDate(ServiceCommon.getFullCurrentDate());
        command.setActiveStatus(ServiceCommon.ONE);
        commandGateway.sendAndWait(command);
        response = ResponseCommon.builder().error(false).message(ServiceCommon.ADD_EMPLOYEE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updateAdmin(UpdateAdminRequestModel requestModel) {

        ResponseCommon response = null;
        UpdateAdminCommand command = new UpdateAdminCommand();
        Set<String> errs = new HashSet<>();
        if(!requestModel.getEmail().equals(requestModel.getHdnEmail())) {

            long checkExistEmail = adminRepository.getCountByEmail(requestModel.getEmail());
            if (checkExistEmail > 0) {

                errs.add(ServiceCommon.ERROR_002);
            }
        }
        if(!requestModel.getPhone().equals(requestModel.getHdnPhone())) {

            long checkExistPhone = adminRepository.getCountByPhone(requestModel.getPhone());
            if (checkExistPhone > 0) {

                errs.add(ServiceCommon.ERROR_003);
            }
        }
        if (!errs.isEmpty()) {

            response = ResponseCommon.builder().error(Boolean.TRUE).errorMessage(errs).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        command.setPassword(checkAndReturnPassword(requestModel.getPassword()));
        command.setRole(ServiceCommon.ROLE_ADMIN);
        command.setLastModified(ServiceCommon.getFullCurrentDate());
        command.setActiveStatus(ServiceCommon.ONE);
        commandGateway.sendAndWait(command);
        response = ResponseCommon.builder().error(false).message(ServiceCommon.UPDATE_EMPLOYEE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deleteAdmin(AdminRequestModel requestModel) {

        DeleteAdminCommand command = new DeleteAdminCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.DELETE_EMPLOYEE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String checkAndReturnPassword(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(password.startsWith("$") && checkCount(password) > 1) {

            return password;
        }
        return passwordEncoder.encode(password);
    }

    private int checkCount(String password) {

        int count = 0;
        String[] toArray = password.split("");
        for (String s : toArray) {

            if(s.equals("$")) {

                count++;
            }
        }
        return count;
    }

}