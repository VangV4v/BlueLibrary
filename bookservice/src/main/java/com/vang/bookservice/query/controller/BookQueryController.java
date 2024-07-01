package com.vang.bookservice.query.controller;

import com.vang.bookservice.query.model.BookResponseModel;
import com.vang.bookservice.query.service.BookQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books/")
public class BookQueryController {

    private final BookQueryService bookQueryService;

    @Autowired
    public BookQueryController(BookQueryService bookQueryService) {
        this.bookQueryService = bookQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponseModel> getByBookId(@PathVariable("id") String bookId) {

        return bookQueryService.getByBookId(bookId);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseModel>> getAllBooks() {

        return bookQueryService.getAllBooks();
    }
}