package com.vang.publisherservice.command.service.impl;

import com.vang.publisherservice.command.command.CreatePublisherCommand;
import com.vang.publisherservice.command.command.DeletePublisherCommand;
import com.vang.publisherservice.command.command.UpdatePublisherCommand;
import com.vang.publisherservice.command.model.PublisherRequestModel;
import com.vang.publisherservice.command.service.PublisherCommandService;
import com.vang.publisherservice.common.ResponseCommon;
import com.vang.publisherservice.common.ServiceCommon;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PublisherCommandServiceImpl implements PublisherCommandService {

    private final CommandGateway commandGateway;

    public PublisherCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public ResponseEntity<ResponseCommon> addPublisher(PublisherRequestModel requestModel) {

        CreatePublisherCommand command = new CreatePublisherCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        command.setCountOfBook(ServiceCommon.ZERO);
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.ADD_PUBLISHER_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updatePublisher(PublisherRequestModel requestModel) {

        UpdatePublisherCommand command = new UpdatePublisherCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.UPDATE_PUBLISHER_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deletePublisher(PublisherRequestModel requestModel) {

        DeletePublisherCommand command = new DeletePublisherCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis() + new Random().nextInt(100, 1000));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().error(false).message(ServiceCommon.DELETE_PUBLISHER_SUCCESS).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}