package com.vang.authadminservice.service;

import com.vang.authadminservice.common.AuthenticateResponseCommon;
import com.vang.authadminservice.model.AuthRequestModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticateAdminService {

    ResponseEntity<AuthenticateResponseCommon> authenticate(AuthRequestModel model);
}