package com.vang.authorservice.query.controller;

import com.vang.authorservice.query.model.AuthorResponseModel;
import com.vang.authorservice.query.service.AuthorQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors/")
public class AuthorQueryController {

    private final AuthorQueryService authorQueryService;

    @Autowired
    public AuthorQueryController(AuthorQueryService authorQueryService) {
        this.authorQueryService = authorQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorResponseModel> getAuthorById(@PathVariable("id") String id) {

        return authorQueryService.getAuthorById(id);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseModel>> getAuthors() {

        return authorQueryService.getAllAuthors();
    }

}