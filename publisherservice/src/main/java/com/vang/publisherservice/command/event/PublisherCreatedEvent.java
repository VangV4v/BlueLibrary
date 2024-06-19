package com.vang.publisherservice.command.event;

import lombok.Data;

@Data
public class PublisherCreatedEvent {

    private Long generateAggregateId;
    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;
}