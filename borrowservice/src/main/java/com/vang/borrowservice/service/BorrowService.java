package com.vang.borrowservice.service;

import com.vang.borrowservice.model.BorrowRequestModel;
import com.vang.borrowservice.model.BorrowResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BorrowService {

    ResponseEntity<String> addBorrow(BorrowRequestModel requestModel);

    ResponseEntity<List<BorrowResponseModel>> getBorrowByUserId();
}