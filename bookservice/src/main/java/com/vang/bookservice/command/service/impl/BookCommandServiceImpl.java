package com.vang.bookservice.command.service.impl;

import com.google.gson.Gson;
import com.vang.bookservice.command.command.CreateBookCommand;
import com.vang.bookservice.command.command.DeleteBookCommand;
import com.vang.bookservice.command.command.UpdateBookCommand;
import com.vang.bookservice.command.model.BookRequestModel;
import com.vang.bookservice.command.model.UpdateBookRequestModel;
import com.vang.bookservice.command.service.BookCommandService;
import com.vang.bookservice.common.ResponseCommon;
import com.vang.bookservice.common.ServiceCommon;
import com.vang.bookservice.grpc.grpc.GetAuthorByIdClientImpl;
import com.vang.bookservice.grpc.grpc.GetPublisherByIdClientImpl;
import com.vang.bookservice.grpc.grpc.GetTypeByIdClientImpl;
import com.vang.bookservice.grpc.grpcmodel.AuthorJsonModel;
import com.vang.bookservice.grpc.grpcmodel.PublisherJsonModel;
import com.vang.bookservice.grpc.grpcmodel.TypeJsonModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Random;

@Service
public class BookCommandServiceImpl implements BookCommandService {

    private final CommandGateway commandGateway;
    private final GetAuthorByIdClientImpl getAuthorByIdClient;
    private final GetTypeByIdClientImpl getTypeByIdClient;
    private final GetPublisherByIdClientImpl getPublisherByIdClient;

    @Autowired
    public BookCommandServiceImpl(CommandGateway commandGateway, GetAuthorByIdClientImpl getAuthorByIdClient, GetTypeByIdClientImpl getTypeByIdClient, GetPublisherByIdClientImpl getPublisherByIdClient) {
        this.commandGateway = commandGateway;
        this.getAuthorByIdClient = getAuthorByIdClient;
        this.getTypeByIdClient = getTypeByIdClient;
        this.getPublisherByIdClient = getPublisherByIdClient;
    }

    @Override
    public ResponseEntity<ResponseCommon> addBook(BookRequestModel requestModel) {

        Gson gson = new Gson();
        CreateBookCommand command = new CreateBookCommand();
        BeanUtils.copyProperties(requestModel, command);
        //Grpc
        String authorResponseJson = getAuthorByIdClient.getAuthorById(requestModel.getAuthorId());
        String typeResponseJson = getTypeByIdClient.getTypeById(requestModel.getTypeId());
        String publisherResponseJson = getPublisherByIdClient.getPublisherById(requestModel.getPublisherId());
        PublisherJsonModel publisherJsonModel = gson.fromJson(publisherResponseJson, PublisherJsonModel.class);
        AuthorJsonModel authorJsonModel = gson.fromJson(authorResponseJson, AuthorJsonModel.class);
        TypeJsonModel typeJsonModel = gson.fromJson(typeResponseJson, TypeJsonModel.class);
        //Image
        if(!ObjectUtils.isEmpty(requestModel.getImageData())) {

            try {

                command.setImageData(requestModel.getImageData().getBytes());
                command.setImageName(requestModel.getImageData().getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        command.setGenerateAggregateId(System.currentTimeMillis()+ (new Random().nextInt(100,1000)));
        command.setAuthorId(authorJsonModel.getAuthorId());
        command.setAuthorDetail(authorResponseJson);
        command.setTypeId(typeJsonModel.getTypeId());
        command.setTypeDetail(typeResponseJson);
        command.setPublisherId(publisherJsonModel.getPublisherId());
        command.setPublisherDetail(publisherResponseJson);
        command.setCreatedDate(ServiceCommon.getFullCurrentDate());
        command.setStatus(ServiceCommon.ONE);
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().message(ServiceCommon.ADD_BOOK_SUCCESS).error(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> updateBook(UpdateBookRequestModel requestModel) {

        Gson gson = new Gson();
        UpdateBookCommand command = new UpdateBookCommand();
        BeanUtils.copyProperties(requestModel, command);
        //Grpc
        if(!requestModel.getHdnAuthorId().equals(requestModel.getAuthorId())) {

            String authorResponseJson = getAuthorByIdClient.getAuthorById(requestModel.getAuthorId());
            AuthorJsonModel authorJsonModel = gson.fromJson(authorResponseJson, AuthorJsonModel.class);
            command.setAuthorId(authorJsonModel.getAuthorId());
            command.setAuthorDetail(authorResponseJson);
        }
        if(!requestModel.getHdnTypeId().equals(requestModel.getTypeId())) {

            String typeResponseJson = getTypeByIdClient.getTypeById(requestModel.getTypeId());
            TypeJsonModel typeJsonModel = gson.fromJson(typeResponseJson, TypeJsonModel.class);
            command.setTypeId(typeJsonModel.getTypeId());
            command.setTypeDetail(typeResponseJson);
        }
        if(!requestModel.getHdnPublisherId().equals(requestModel.getPublisherId())) {

            String publisherResponseJson = getPublisherByIdClient.getPublisherById(requestModel.getPublisherId());
            PublisherJsonModel publisherJsonModel = gson.fromJson(publisherResponseJson, PublisherJsonModel.class);
            command.setPublisherId(publisherJsonModel.getPublisherId());
            command.setPublisherDetail(publisherResponseJson);
        }
        //Image
        if(!ObjectUtils.isEmpty(requestModel.getImageData())) {

            try {
                command.setImageData(requestModel.getImageData().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        command.setGenerateAggregateId(System.currentTimeMillis()+ (new Random().nextInt(100,1000)));
        command.setLastModified(ServiceCommon.getFullCurrentDate());
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().message(ServiceCommon.UPDATE_BOOK_SUCCESS).error(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCommon> deleteBook(BookRequestModel requestModel) {

        DeleteBookCommand command = new DeleteBookCommand();
        BeanUtils.copyProperties(requestModel, command);
        command.setGenerateAggregateId(System.currentTimeMillis()+ (new Random().nextInt(100,1000)));
        commandGateway.sendAndWait(command);
        ResponseCommon response = ResponseCommon.builder().message(ServiceCommon.DELETE_BOOK_SUCCESS).error(false).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}