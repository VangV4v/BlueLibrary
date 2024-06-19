package com.vang.authorservice.query.service.impl;

import com.vang.authorservice.query.model.AuthorResponseModel;
import com.vang.authorservice.query.queries.GetAllAuthors;
import com.vang.authorservice.query.queries.GetByAuthorId;
import com.vang.authorservice.query.service.AuthorQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorQueryServiceImpl implements AuthorQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public AuthorQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<AuthorResponseModel> getAuthorById(String id) {

        GetByAuthorId authorId = new GetByAuthorId(id);
        AuthorResponseModel responseModel = queryGateway.query(authorId, ResponseTypes.instanceOf(AuthorResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AuthorResponseModel>> getAllAuthors() {

        GetAllAuthors allAuthors = new GetAllAuthors();
        List<AuthorResponseModel> responseModels = queryGateway.query(allAuthors, ResponseTypes.multipleInstancesOf(AuthorResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

}