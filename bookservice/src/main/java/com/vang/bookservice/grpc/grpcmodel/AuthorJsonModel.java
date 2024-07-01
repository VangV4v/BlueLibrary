package com.vang.bookservice.grpc.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorJsonModel implements Serializable {

    private String authorId;
    private String fullName;
    private String dateOfBirth;
    private String country;
    private int countOfBook;
}