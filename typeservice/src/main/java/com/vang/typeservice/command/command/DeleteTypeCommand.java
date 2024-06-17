package com.vang.typeservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteTypeCommand {
    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String TypeId;
}