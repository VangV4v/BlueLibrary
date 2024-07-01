package com.vang.userservice.command.service.impl;

import com.vang.userservice.command.command.CreateUserCommand;
import com.vang.userservice.command.command.DeleteUserCommand;
import com.vang.userservice.command.command.UpdateUserCommand;
import com.vang.userservice.command.model.UpdateUserRequestModel;
import com.vang.userservice.command.model.UserRequestModel;
import com.vang.userservice.command.service.UserCommandService;
import com.vang.userservice.common.ResponseCommon;
import com.vang.userservice.common.ServiceCommon;
import com.vang.userservice.data.UserRepository;
import org.apache.commons.lang.StringUtils;
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
public class UserCommandServiceImpl implements UserCommandService {

    private final CommandGateway commandGateway;
    private final UserRepository userRepository;

    @Autowired
    public UserCommandServiceImpl(CommandGateway commandGateway, UserRepository userRepository) {
        this.commandGateway = commandGateway;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseCommon> addUser(UserRequestModel requestModel) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CreateUserCommand command = new CreateUserCommand();
        long checkExistUsername = userRepository.getCountByUsername(requestModel.getUsername());
        long checkExistEmail = userRepository.getCountByEmail(requestModel.getEmail());
        long checkExistPhone = userRepository.getCountByPhone(requestModel.getPhone());
        Set<String> errs = new HashSet<>();
        if(!StringUtils.isEmpty(requestModel.getUsername()) && checkExistUsername > 0) {

            errs.add(ServiceCommon.ERROR_001);
        }
        if(!StringUtils.isEmpty(requestModel.getEmail()) && checkExistEmail > 0) {

            errs.add(ServiceCommon.ERROR_002);
        }
        if(!StringUtils.isEmpty(requestModel.getPhone()) && checkExistPhone > 0) {

            errs.add(ServiceCommon.ERROR_003);
        }
        if(!errs.isEmpty()) {

            ResponseCommon response = ResponseCommon.builder().error(true).errorMessage(errs).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis()+(new Random().nextInt(100, 1000)));
        command.setCreatedDate(ServiceCommon.getFullCurrentDate());
        command.setActiveStatus(1);
        command.setAvatar(ServiceCommon.DEFAULT_IMAGE);
        command.setRole(ServiceCommon.ROLE_USER);
        command.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.ADD_USER_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updateUser(UpdateUserRequestModel requestModel) {

        UpdateUserCommand command = new UpdateUserCommand();
        BeanUtils.copyProperties(requestModel, command);
        Set<String> errs = new HashSet<>();
        if(!requestModel.getPhone().equals(requestModel.getHdnOldPhone())) {

            long checkExistPhone = userRepository.getCountByPhone(requestModel.getPhone());
            if(!StringUtils.isEmpty(requestModel.getPhone()) && checkExistPhone > 0) {

                errs.add(ServiceCommon.ERROR_003);
            }
        }
        if(!requestModel.getEmail().equals(requestModel.getHdnOldEmail())) {

            long checkExistEmail = userRepository.getCountByEmail(requestModel.getEmail());
            if(!StringUtils.isEmpty(requestModel.getEmail()) && checkExistEmail > 0) {

                errs.add(ServiceCommon.ERROR_002);
            }
        }
        if(!errs.isEmpty()) {

            ResponseCommon response = ResponseCommon.builder().error(true).errorMessage(errs).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(!ObjectUtils.isEmpty(requestModel.getImage())) {

            try {
                command.setImageData(requestModel.getImage().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        command.setGenerateAggregateId(System.currentTimeMillis()+(new Random().nextInt(100, 1000)));
        command.setCreatedDate(ServiceCommon.getFullCurrentDate());
        command.setRole(ServiceCommon.ROLE_USER);
        command.setPassword(checkAndReturnPassword(requestModel.getPassword()));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.UPDATE_USER_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deleteUser(UserRequestModel requestModel) {

        DeleteUserCommand command = new DeleteUserCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis()+(new Random().nextInt(100, 1000)));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.DELETE_USER_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String checkAndReturnPassword(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(password.startsWith("$") && password.contains("$")) {

            return password;
        }
        return passwordEncoder.encode(password);
    }
}
