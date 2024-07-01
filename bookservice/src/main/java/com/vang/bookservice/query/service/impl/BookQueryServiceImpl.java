package com.vang.bookservice.query.service.impl;

import com.vang.bookservice.query.model.BookResponseModel;
import com.vang.bookservice.query.queries.GetAllBooks;
import com.vang.bookservice.query.queries.GetByBookId;
import com.vang.bookservice.query.service.BookQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryServiceImpl implements BookQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public BookQueryServiceImpl(QueryGateway queryGateway) {

        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<BookResponseModel> getByBookId(String bookId) {

        GetByBookId byBookId = new GetByBookId(bookId);
        BookResponseModel responseModel = queryGateway.query(byBookId, ResponseTypes.instanceOf(BookResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookResponseModel>> getAllBooks() {

        GetAllBooks allBooks = new GetAllBooks();
        List<BookResponseModel> responseModels = queryGateway.query(allBooks, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

}