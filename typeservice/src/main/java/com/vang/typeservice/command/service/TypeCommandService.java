package com.vang.typeservice.command.service;

import com.vang.typeservice.command.model.TypeRequestModel;
import com.vang.typeservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface TypeCommandService {

    ResponseEntity<ResponseCommon> addType(TypeRequestModel requestModel);
    ResponseEntity<ResponseCommon> updateType(TypeRequestModel requestModel);
    ResponseEntity<ResponseCommon> deleteType(TypeRequestModel requestModel);
}