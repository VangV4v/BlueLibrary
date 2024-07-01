package com.vang.bookservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteBookCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String bookId;
    private String image;
}