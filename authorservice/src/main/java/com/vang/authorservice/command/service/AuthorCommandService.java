package com.vang.authorservice.command.service;

import com.vang.authorservice.command.model.AuthorRequestModel;
import com.vang.authorservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface AuthorCommandService {

    ResponseEntity<ResponseCommon> addAuthor(AuthorRequestModel requestModel);

    ResponseEntity<ResponseCommon> updateAuthor(AuthorRequestModel requestModel);

    ResponseEntity<ResponseCommon> deleteAuthor(AuthorRequestModel requestModel);
}