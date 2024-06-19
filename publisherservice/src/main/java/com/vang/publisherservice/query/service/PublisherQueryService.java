package com.vang.publisherservice.query.service;

import com.vang.publisherservice.query.model.PublisherResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublisherQueryService {

    ResponseEntity<PublisherResponseModel> getByPublisherId(String publisherId);

    ResponseEntity<List<PublisherResponseModel>> getAllPublisher();
}