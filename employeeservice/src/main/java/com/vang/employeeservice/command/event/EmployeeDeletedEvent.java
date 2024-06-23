package com.vang.employeeservice.command.event;

import lombok.Data;

@Data
public class EmployeeDeletedEvent {

    private Long generateAggregateId;
    private String employeeId;
    private String avatar;
}