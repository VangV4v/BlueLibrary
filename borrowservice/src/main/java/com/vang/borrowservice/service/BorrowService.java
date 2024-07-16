package com.vang.borrowservice.service;

import com.vang.borrowservice.model.BorrowRequestModel;
import org.springframework.http.ResponseEntity;

public interface BorrowService {

    ResponseEntity<String> addBorrow(BorrowRequestModel requestModel);
}