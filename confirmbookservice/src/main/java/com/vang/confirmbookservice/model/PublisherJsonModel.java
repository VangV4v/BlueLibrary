package com.vang.confirmbookservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublisherJsonModel implements Serializable {

    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;
}