package com.vang.bookservice.command.event;

import lombok.Data;

@Data
public class BookDeletedEvent {

    private Long generateAggregateId;
    private String bookId;
    private String image;
}