package com.vang.publisherservice.query.controller;

import com.vang.publisherservice.query.model.PublisherResponseModel;
import com.vang.publisherservice.query.service.PublisherQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers/")
public class PublisherQueryController {

    private final PublisherQueryService publisherQueryService;

    @Autowired
    public PublisherQueryController(PublisherQueryService publisherQueryService) {
        this.publisherQueryService = publisherQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PublisherResponseModel> getByPublisherId(@PathVariable("id") String publisherId) {

        return publisherQueryService.getByPublisherId(publisherId);
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseModel>> getAll() {

        return publisherQueryService.getAllPublisher();
    }

}