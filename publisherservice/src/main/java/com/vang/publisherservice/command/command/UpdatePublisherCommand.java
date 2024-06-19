package com.vang.publisherservice.command.command;

import lombok.Data;

@Data
public class UpdatePublisherCommand {

    private Long generateAggregateId;
    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;
}