package com.vang.bookservice.command.service;

import com.vang.bookservice.command.model.BookRequestModel;
import com.vang.bookservice.command.model.UpdateBookRequestModel;
import com.vang.bookservice.common.ResponseCommon;
import org.springframework.http.ResponseEntity;

public interface BookCommandService {

    ResponseEntity<ResponseCommon> addBook(BookRequestModel requestModel);

    ResponseEntity<ResponseCommon> updateBook(UpdateBookRequestModel requestModel);

    ResponseEntity<ResponseCommon> deleteBook(BookRequestModel requestModel);
}