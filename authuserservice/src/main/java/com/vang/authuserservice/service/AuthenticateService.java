package com.vang.authuserservice.service;

import com.vang.authuserservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    ResponseEntity<String> authenticate(AuthRequestModel requestModel);
}