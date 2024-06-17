package com.vang.typeservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateTypeCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String TypeId;
    private String TypeName;
    private String TypeDescription;
    private int CountOfBook;
}