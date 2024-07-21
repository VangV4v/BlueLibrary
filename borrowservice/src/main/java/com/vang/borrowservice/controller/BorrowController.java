package com.vang.borrowservice.controller;

import com.vang.borrowservice.model.BorrowRequestModel;
import com.vang.borrowservice.model.BorrowResponseModel;
import com.vang.borrowservice.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrows/")
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("add/")
    public ResponseEntity<String> addBorrow(@RequestBody BorrowRequestModel requestModel) {

        return borrowService.addBorrow(requestModel);
    }

    @GetMapping("user/")
    public ResponseEntity<List<BorrowResponseModel>> getAllBorrows() {

        return borrowService.getBorrowByUserId();
    }
}