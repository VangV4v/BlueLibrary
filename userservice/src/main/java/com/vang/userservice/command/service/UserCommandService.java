package com.vang.userservice.command.service;

import com.vang.userservice.command.model.UpdateUserRequestModel;
import com.vang.userservice.command.model.UserRequestModel;
import com.vang.userservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface UserCommandService {

    ResponseEntity<ResponseCommon> addUser(UserRequestModel requestModel);

    ResponseEntity<ResponseCommon> updateUser(UpdateUserRequestModel requestModel);

    ResponseEntity<ResponseCommon> deleteUser(UserRequestModel requestModel);
}