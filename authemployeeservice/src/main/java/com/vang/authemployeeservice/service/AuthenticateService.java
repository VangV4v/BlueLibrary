package com.vang.authemployeeservice.service;

import com.vang.authemployeeservice.common.AuthenticateResponseCommon;
import com.vang.authemployeeservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    ResponseEntity<AuthenticateResponseCommon> authenticate(AuthRequestModel requestModel);
}