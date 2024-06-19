package com.vang.publisherservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublisherResponseModel implements Serializable {

    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;

    public void initialize() {
        this.publisherId = null;
        this.name = null;
        this.description = null;
        this.address = null;
        this.countOfBook = 0;
    }
}