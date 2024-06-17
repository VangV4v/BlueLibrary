package com.vang.typeservice.query.controller;

import com.vang.typeservice.query.model.TypeResponseModel;
import com.vang.typeservice.query.service.TypeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/types/")
public class TypeQueryController {

    private final TypeQueryService service;

    @Autowired
    public TypeQueryController(TypeQueryService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<TypeResponseModel> getByTypeId(@PathVariable("id") String typeId) {
        return service.getByTypeId(typeId);
    }

    @GetMapping
    public ResponseEntity<List<TypeResponseModel>> getAllTypes() {
        return service.getAllTypes();
    }
}