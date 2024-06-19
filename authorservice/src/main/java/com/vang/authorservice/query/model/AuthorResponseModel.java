package com.vang.authorservice.query.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class AuthorResponseModel implements Serializable {

    private String authorId;
    private String fullName;
    private String dateOfBirth;
    private String country;
    private int countOfBook;

    public void initialize() {
        this.authorId = null;
        this.fullName = null;
        this.dateOfBirth = null;
        this.country = null;
        this.countOfBook = 0;
    }

}