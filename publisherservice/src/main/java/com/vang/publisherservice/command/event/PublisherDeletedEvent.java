package com.vang.publisherservice.command.event;

import lombok.Data;

@Data
public class PublisherDeletedEvent {

    private Long generateAggregateId;
    private String publisherId;
}