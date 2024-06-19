package com.vang.typeservice.command.service.impl;

import com.vang.typeservice.command.command.CreateTypeCommand;
import com.vang.typeservice.command.command.DeleteTypeCommand;
import com.vang.typeservice.command.command.UpdateTypeCommand;
import com.vang.typeservice.command.model.TypeRequestModel;
import com.vang.typeservice.command.service.TypeCommandService;
import com.vang.typeservice.common.TypeServiceCommon;
import com.vang.typeservice.common.ResponseCommon;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TypeCommandServiceImpl implements TypeCommandService {

    private final CommandGateway commandGateway;

    @Autowired
    public TypeCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<ResponseCommon> addType(TypeRequestModel requestModel) {

        CreateTypeCommand command = new CreateTypeCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis()+new Random().nextInt(100, 1000));
        command.setCountOfBook(TypeServiceCommon.ZERO);
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(TypeServiceCommon.ADD_TYPE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updateType(TypeRequestModel requestModel) {

        UpdateTypeCommand command = new UpdateTypeCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis()+new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(TypeServiceCommon.UPDATE_TYPE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deleteType(TypeRequestModel requestModel) {

        DeleteTypeCommand command = new DeleteTypeCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis()+new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(TypeServiceCommon.DELETE_TYPE_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}