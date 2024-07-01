package com.vang.userservice.query.service.impl;

import com.vang.userservice.query.model.UserResponseModel;
import com.vang.userservice.query.queries.GetAllUsers;
import com.vang.userservice.query.queries.GetByUserId;
import com.vang.userservice.query.service.UserQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public UserQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<UserResponseModel> getByUserId(String userId) {

        GetByUserId byUserId = new GetByUserId(userId);
        UserResponseModel responseModel = queryGateway.query(byUserId, ResponseTypes.instanceOf(UserResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {

        GetAllUsers allUsers = new GetAllUsers();
        List<UserResponseModel> responseModels = queryGateway.query(allUsers, ResponseTypes.multipleInstancesOf(UserResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }
}