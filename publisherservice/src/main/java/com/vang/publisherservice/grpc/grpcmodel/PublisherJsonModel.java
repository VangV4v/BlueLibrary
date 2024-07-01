package com.vang.publisherservice.grpc.grpcmodel;

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