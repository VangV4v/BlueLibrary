package com.vang.authorservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteAuthorCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String authorId;
}