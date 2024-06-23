package com.vang.authorservice.command.service.impl;

import com.vang.authorservice.command.command.CreateAuthorCommand;
import com.vang.authorservice.command.command.DeleteAuthorCommand;
import com.vang.authorservice.command.command.UpdateAuthorCommand;
import com.vang.authorservice.command.model.AuthorRequestModel;
import com.vang.authorservice.command.service.AuthorCommandService;
import com.vang.authorservice.common.ResponseCommon;
import com.vang.authorservice.common.ServiceCommon;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorCommandServiceImpl implements AuthorCommandService {

    private final CommandGateway commandGateway;

    @Autowired
    public AuthorCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<ResponseCommon> addAuthor(AuthorRequestModel requestModel) {

        CreateAuthorCommand command = new CreateAuthorCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        command.setCountOfBook(ServiceCommon.ZERO);
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.ADD_AUTHOR_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updateAuthor(AuthorRequestModel requestModel) {

        UpdateAuthorCommand command = new UpdateAuthorCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.UPDATE_AUTHOR_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deleteAuthor(AuthorRequestModel requestModel) {

        DeleteAuthorCommand command = new DeleteAuthorCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.DELETE_AUTHOR_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
