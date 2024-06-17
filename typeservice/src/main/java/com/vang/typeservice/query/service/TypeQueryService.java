package com.vang.typeservice.query.service;

import com.vang.typeservice.query.model.TypeResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TypeQueryService {
    ResponseEntity<TypeResponseModel> getByTypeId(String typeId);
    ResponseEntity<List<TypeResponseModel>> getAllTypes();
}