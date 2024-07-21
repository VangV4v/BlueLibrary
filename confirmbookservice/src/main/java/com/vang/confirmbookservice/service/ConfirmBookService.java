package com.vang.confirmbookservice.service;

import com.vang.confirmbookservice.common.ConfirmResponseCommon;
import com.vang.confirmbookservice.model.BookRequestModel;
import com.vang.confirmbookservice.model.BookResponseModel;
import com.vang.confirmbookservice.model.ConfirmBookRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConfirmBookService {

    ResponseEntity<List<BookResponseModel>> getAllBooksWait();

    ResponseEntity<ConfirmResponseCommon> confirmBook(ConfirmBookRequestModel requestModel);
}