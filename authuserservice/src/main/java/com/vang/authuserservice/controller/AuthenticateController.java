package com.vang.authuserservice.controller;

import com.vang.authuserservice.common.AuthenticateResponseCommon;
import com.vang.authuserservice.model.AuthRequestModel;
import com.vang.authuserservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/user/")
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