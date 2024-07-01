package com.vang.userservice.command.controller;

import com.vang.userservice.command.model.UpdateUserRequestModel;
import com.vang.userservice.command.model.UserRequestModel;
import com.vang.userservice.command.service.UserCommandService;
import com.vang.userservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class UserCommandController {

    private final UserCommandService userCommandService;

    @Autowired
    public UserCommandController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addUser(@RequestBody UserRequestModel requestModel) {

        return userCommandService.addUser(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updateUser(@ModelAttribute UpdateUserRequestModel requestModel) {

        return userCommandService.updateUser(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deleteUser(@RequestBody UserRequestModel requestModel) {

        return userCommandService.deleteUser(requestModel);
    }
}