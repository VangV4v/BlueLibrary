package com.vang.bookservice.command.controller;

import com.vang.bookservice.command.model.BookRequestModel;
import com.vang.bookservice.command.model.UpdateBookRequestModel;
import com.vang.bookservice.command.service.BookCommandService;
import com.vang.bookservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books/")
public class BookCommandController {

    private final BookCommandService bookCommandService;

    @Autowired
    public BookCommandController(BookCommandService bookCommandService) {
        this.bookCommandService = bookCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addBook(@ModelAttribute BookRequestModel requestModel) {

        return bookCommandService.addBook(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updateBook(@ModelAttribute UpdateBookRequestModel requestModel) {

        return bookCommandService.updateBook(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deleteBook(@RequestBody BookRequestModel requestModel) {

        return bookCommandService.deleteBook(requestModel);
    }

}