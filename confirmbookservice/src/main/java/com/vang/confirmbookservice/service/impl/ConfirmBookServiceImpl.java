package com.vang.confirmbookservice.service.impl;

import com.google.gson.Gson;
import com.vang.confirmbookservice.common.ConfirmResponseCommon;
import com.vang.confirmbookservice.data.BookRepository;
import com.vang.confirmbookservice.data.Books;
import com.vang.confirmbookservice.model.*;
import com.vang.confirmbookservice.service.ConfirmBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmBookServiceImpl implements ConfirmBookService {

    private final BookRepository bookRepository;

    @Autowired
    public ConfirmBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<List<BookResponseModel>> getAllBooksWait() {

        Gson gson = new Gson();
        List<Books> books = bookRepository.findAll().stream().filter(obj -> obj.getStatus() == 1).toList();
        List<BookResponseModel> responseModels = new ArrayList<>();
        books.forEach(obj -> {

            BookResponseModel model = new BookResponseModel();
            TypeJsonModel typeJsonModel = gson.fromJson(obj.getTypeDetail(), TypeJsonModel.class);
            AuthorJsonModel authorJsonModel = gson.fromJson(obj.getAuthorDetail(), AuthorJsonModel.class);
            PublisherJsonModel publisherJsonModel = gson.fromJson(obj.getPublisherDetail(), PublisherJsonModel.class);
            BeanUtils.copyProperties(obj, model);
            model.setTypeDetail(typeJsonModel);
            model.setAuthorDetail(authorJsonModel);
            model.setPublisherDetail(publisherJsonModel);
            responseModels.add(model);

        });

        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<ConfirmResponseCommon> confirmBook(ConfirmBookRequestModel requestModel) {

        int status = bookRepository.updateByBookIdAndStatus(requestModel.getBookId(), requestModel.getStatus());
        ConfirmResponseCommon response;
        if(status > 0) {

            response = ConfirmResponseCommon.builder().isSuccess(true).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            response = ConfirmResponseCommon.builder().isSuccess(false).isError(true).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}