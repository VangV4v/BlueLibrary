package com.vang.userservice.query.service;

import com.vang.userservice.query.model.UserResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserQueryService {

    ResponseEntity<UserResponseModel> getByUserId(String userId);

    ResponseEntity<List<UserResponseModel>> getAllUsers();
}