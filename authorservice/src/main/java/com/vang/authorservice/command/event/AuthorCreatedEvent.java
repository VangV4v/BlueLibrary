package com.vang.authorservice.command.event;

import lombok.Data;

@Data
public class AuthorCreatedEvent {

    private Long generateAggregateId;
    private String authorId;
    private String fullName;
    private String dateOfBirth;
    private String country;
    private int countOfBook;
}