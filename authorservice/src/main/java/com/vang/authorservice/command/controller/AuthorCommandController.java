package com.vang.authorservice.command.controller;

import com.vang.authorservice.command.model.AuthorRequestModel;
import com.vang.authorservice.command.service.AuthorCommandService;
import com.vang.authorservice.common.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors/")
public class AuthorCommandController {

    private final AuthorCommandService authorCommandService;

    @Autowired
    public AuthorCommandController(AuthorCommandService authorCommandService) {
        this.authorCommandService = authorCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommon> addAuthor(@RequestBody AuthorRequestModel requestModel) {

        return authorCommandService.addAuthor(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseCommon> updateAuthor(@RequestBody AuthorRequestModel requestModel) {

        return authorCommandService.updateAuthor(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseCommon> deleteAuthor(@RequestBody AuthorRequestModel requestModel) {

        return authorCommandService.deleteAuthor(requestModel);
    }
}