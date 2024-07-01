package com.vang.bookservice.query.service;

import com.vang.bookservice.query.model.BookResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookQueryService {

    ResponseEntity<BookResponseModel> getByBookId(String bookId);

    ResponseEntity<List<BookResponseModel>> getAllBooks();
}