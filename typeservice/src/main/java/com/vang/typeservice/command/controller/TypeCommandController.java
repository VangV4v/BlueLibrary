package com.vang.typeservice.command.controller;

import com.vang.typeservice.command.model.TypeRequestModel;
import com.vang.typeservice.command.service.TypeCommandService;
import com.vang.typeservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/types/")
public class TypeCommandController {

    private final TypeCommandService typeCommandService;

    @Autowired
    public TypeCommandController(TypeCommandService typeCommandService) {
        this.typeCommandService = typeCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addType(@RequestBody TypeRequestModel requestModel) {
        return typeCommandService.addType(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updateType(@RequestBody TypeRequestModel requestModel) {
        return typeCommandService.updateType(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deleteType(@RequestBody TypeRequestModel requestModel) {
        return typeCommandService.deleteType(requestModel);
    }

}