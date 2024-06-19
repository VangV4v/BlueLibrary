package com.vang.publisherservice.query.service.impl;

import com.vang.publisherservice.query.model.PublisherResponseModel;
import com.vang.publisherservice.query.queries.GetAllPublishers;
import com.vang.publisherservice.query.queries.GetByPublisherId;
import com.vang.publisherservice.query.service.PublisherQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherQueryServiceImpl implements PublisherQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public PublisherQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<PublisherResponseModel> getByPublisherId(String publisherId) {

        GetByPublisherId byPublisherId = new GetByPublisherId(publisherId);
        PublisherResponseModel responseModel = queryGateway.query(byPublisherId, ResponseTypes.instanceOf(PublisherResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PublisherResponseModel>> getAllPublisher() {

        GetAllPublishers allPublishers = new GetAllPublishers();
        List<PublisherResponseModel> responseModels = queryGateway.query(allPublishers, ResponseTypes.multipleInstancesOf(PublisherResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }
}