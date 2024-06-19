package com.vang.authorservice.query.projection;

import com.vang.authorservice.data.AuthorRepository;
import com.vang.authorservice.data.Authors;
import com.vang.authorservice.query.model.AuthorResponseModel;
import com.vang.authorservice.query.queries.GetAllAuthors;
import com.vang.authorservice.query.queries.GetByAuthorId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorQueryProjection {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorQueryProjection(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryHandler
    public AuthorResponseModel getAuthorById(GetByAuthorId authorId) {

        Authors authors = authorRepository.findById(authorId.getAuthorId()).orElse(new Authors());
        AuthorResponseModel responseModel = new AuthorResponseModel();
        if(StringUtils.isEmpty(authors.getAuthorId())) {
            responseModel.initialize();
        } else {
            BeanUtils.copyProperties(authors, responseModel);
        }
        return responseModel;
    }

    @QueryHandler
    public List<AuthorResponseModel> getAllAuthors(GetAllAuthors allAuthors) {

        List<Authors> authors = authorRepository.findAll();
        List<AuthorResponseModel> responseModels = new ArrayList<>();
        authors.forEach(e -> {
            AuthorResponseModel responseModel = new AuthorResponseModel();
            BeanUtils.copyProperties(e, responseModel);
            responseModels.add(responseModel);
        });
        return responseModels;
    }

}