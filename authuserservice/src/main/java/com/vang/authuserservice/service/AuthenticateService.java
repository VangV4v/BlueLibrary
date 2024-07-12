package com.vang.authuserservice.service;

import com.vang.authuserservice.common.AuthenticateResponseCommon;
import com.vang.authuserservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    ResponseEntity<AuthenticateResponseCommon> authenticate(AuthRequestModel requestModel);
}