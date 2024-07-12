package com.vang.adminservice.command.event;

import lombok.Data;

@Data
public class AdminDeletedEvent {

    private Long generateAggregateId;
    private String adminId;
}