package com.vang.authorservice.command.event;

import lombok.Data;

@Data
public class AuthorDeletedEvent {

    private Long generateAggregateId;
    private String authorId;
}