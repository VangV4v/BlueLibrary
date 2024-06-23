package com.vang.employeeservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteEmployeeCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String employeeId;
    private String avatar;
}