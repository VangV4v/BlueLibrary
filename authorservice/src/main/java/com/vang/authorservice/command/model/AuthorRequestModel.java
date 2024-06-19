package com.vang.authorservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorRequestModel implements Serializable {

    private String authorId;
    private String fullName;
    private String dateOfBirth;
    private String country;
    private int countOfBook;
}