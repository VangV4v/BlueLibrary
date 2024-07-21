package com.vang.confirmbookservice.controller;

import com.vang.confirmbookservice.common.ConfirmResponseCommon;
import com.vang.confirmbookservice.model.BookResponseModel;
import com.vang.confirmbookservice.model.ConfirmBookRequestModel;
import com.vang.confirmbookservice.service.ConfirmBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/confirm-books/")
public class ConfirmResponseController {

    private final ConfirmBookService confirmBookService;

    @Autowired
    public ConfirmResponseController(ConfirmBookService confirmBookService) {
        this.confirmBookService = confirmBookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseModel>> getAllBooksWait() {

        return confirmBookService.getAllBooksWait();
    }

    @PutMapping
    public ResponseEntity<ConfirmResponseCommon> confirmBook(@RequestBody ConfirmBookRequestModel requestModel) {

        return confirmBookService.confirmBook(requestModel);
    }

}