package com.vang.adminservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteAdminCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String adminId;
}