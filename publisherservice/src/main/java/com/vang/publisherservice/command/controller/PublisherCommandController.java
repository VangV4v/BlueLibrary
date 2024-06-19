package com.vang.publisherservice.command.controller;

import com.vang.publisherservice.command.model.PublisherRequestModel;
import com.vang.publisherservice.command.service.PublisherCommandService;
import com.vang.publisherservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publishers/")
public class PublisherCommandController {

    private final PublisherCommandService publisherCommandService;

    @Autowired
    public PublisherCommandController(PublisherCommandService publisherCommandService) {
        this.publisherCommandService = publisherCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addPublisher(@RequestBody PublisherRequestModel requestModel) {

        return publisherCommandService.addPublisher(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updatePublisher(@RequestBody PublisherRequestModel requestModel) {

        return publisherCommandService.updatePublisher(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deletePublisher(@RequestBody PublisherRequestModel requestModel) {

        return publisherCommandService.deletePublisher(requestModel);
    }

}