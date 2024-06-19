package com.vang.authorservice.query.service;

import com.vang.authorservice.query.model.AuthorResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorQueryService {

    ResponseEntity<AuthorResponseModel> getAuthorById(String id);

    ResponseEntity<List<AuthorResponseModel>> getAllAuthors();
}