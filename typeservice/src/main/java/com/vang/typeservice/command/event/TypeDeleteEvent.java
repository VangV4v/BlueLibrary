package com.vang.typeservice.command.event;

import lombok.Data;

@Data
public class TypeDeleteEvent {
    private Long generateAggregateId;
    private String TypeId;
}