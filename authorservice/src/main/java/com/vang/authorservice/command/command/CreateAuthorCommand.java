package com.vang.authorservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateAuthorCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String authorId;
    private String fullName;
    private String dateOfBirth;
    private String country;
    private int countOfBook;
}