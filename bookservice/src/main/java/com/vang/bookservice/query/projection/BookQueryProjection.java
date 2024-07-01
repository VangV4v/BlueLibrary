package com.vang.bookservice.query.projection;

import com.google.gson.Gson;
import com.vang.bookservice.data.BookRepository;
import com.vang.bookservice.data.Books;
import com.vang.bookservice.grpc.grpcmodel.AuthorJsonModel;
import com.vang.bookservice.grpc.grpcmodel.PublisherJsonModel;
import com.vang.bookservice.grpc.grpcmodel.TypeJsonModel;
import com.vang.bookservice.query.model.BookResponseModel;
import com.vang.bookservice.query.queries.GetAllBooks;
import com.vang.bookservice.query.queries.GetByBookId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookQueryProjection {

    private final BookRepository bookRepository;

    @Autowired
    public BookQueryProjection(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @QueryHandler
    public BookResponseModel getByBookId(GetByBookId byBookId) {

        Gson gson = new Gson();
        Books books = bookRepository.findById(byBookId.getBookId()).orElse(new Books());
        BookResponseModel responseModel = new BookResponseModel();
        if(StringUtils.isEmpty(books.getBookId())) {

            responseModel.initialize();
        }else {


            BeanUtils.copyProperties(books, responseModel);
            AuthorJsonModel authorJsonModel = gson.fromJson(books.getAuthorDetail(), AuthorJsonModel.class);
            TypeJsonModel typeJsonModel = gson.fromJson(books.getTypeDetail(), TypeJsonModel.class);
            PublisherJsonModel publisherJsonModel = gson.fromJson(books.getPublisherDetail(), PublisherJsonModel.class);
            responseModel.setAuthorDetail(authorJsonModel);
            responseModel.setTypeDetail(typeJsonModel);
            responseModel.setPublisherDetail(publisherJsonModel);
        }
        return responseModel;
    }

    @QueryHandler
    public List<BookResponseModel> getAllBooks(GetAllBooks allBooks) {

        Gson gson = new Gson();
        List<Books> books = bookRepository.findAll();
        List<BookResponseModel> responseModels = new ArrayList<>();
        books.forEach(e -> {
            BookResponseModel responseModel = new BookResponseModel();
            BeanUtils.copyProperties(e, responseModel);
            AuthorJsonModel authorJsonModel = gson.fromJson(e.getAuthorDetail(), AuthorJsonModel.class);
            TypeJsonModel typeJsonModel = gson.fromJson(e.getTypeDetail(), TypeJsonModel.class);
            PublisherJsonModel publisherJsonModel = gson.fromJson(e.getPublisherDetail(), PublisherJsonModel.class);
            responseModel.setAuthorDetail(authorJsonModel);
            responseModel.setTypeDetail(typeJsonModel);
            responseModel.setPublisherDetail(publisherJsonModel);
            responseModels.add(responseModel);
        });
        return responseModels;
    }

}