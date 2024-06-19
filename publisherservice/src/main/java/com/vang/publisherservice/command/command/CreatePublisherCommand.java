package com.vang.publisherservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreatePublisherCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;
}