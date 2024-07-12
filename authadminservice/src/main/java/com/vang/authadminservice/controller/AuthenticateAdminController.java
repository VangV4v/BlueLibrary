package com.vang.authadminservice.controller;

import com.vang.authadminservice.common.AuthenticateResponseCommon;
import com.vang.authadminservice.model.AuthRequestModel;
import com.vang.authadminservice.service.AuthenticateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/admin/")
public class AuthenticateAdminController {

    private final AuthenticateAdminService authenticateAdminService;

    @Autowired
    public AuthenticateAdminController(AuthenticateAdminService authenticateAdminService) {
        this.authenticateAdminService = authenticateAdminService;
    }

    @PostMapping
    public ResponseEntity<AuthenticateResponseCommon> authenticateAdmin(@RequestBody AuthRequestModel requestModel) {

        return authenticateAdminService.authenticate(requestModel);
    }

}