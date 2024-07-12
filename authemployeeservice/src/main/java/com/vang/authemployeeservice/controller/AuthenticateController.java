package com.vang.authemployeeservice.controller;

import com.vang.authemployeeservice.common.AuthenticateResponseCommon;
import com.vang.authemployeeservice.model.AuthRequestModel;
import com.vang.authemployeeservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/employee/")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping
    public ResponseEntity<AuthenticateResponseCommon> authenticate(@RequestBody AuthRequestModel requestModel) {

        return authenticateService.authenticate(requestModel);
    }
}