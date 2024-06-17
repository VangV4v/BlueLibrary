package com.vang.typeservice.command.event;

import lombok.Data;

@Data
public class TypeCreatedEvent {
    private Long generateAggregateId;
    private String TypeId;
    private String TypeName;
    private String TypeDescription;
    private int CountOfBook;
}