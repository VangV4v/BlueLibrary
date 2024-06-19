package com.vang.publisherservice.command.service;

import com.vang.publisherservice.command.model.PublisherRequestModel;
import com.vang.publisherservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface PublisherCommandService {

    ResponseEntity<ResponseCommon> addPublisher(PublisherRequestModel requestModel);

    ResponseEntity<ResponseCommon> updatePublisher(PublisherRequestModel requestModel);

    ResponseEntity<ResponseCommon> deletePublisher(PublisherRequestModel requestModel);
}