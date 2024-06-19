package com.vang.publisherservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublisherRequestModel implements Serializable {

    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;
}