package com.vang.publisherservice.command.command;

import lombok.Data;

@Data
public class DeletePublisherCommand {

    private Long generateAggregateId;
    private String publisherId;
}