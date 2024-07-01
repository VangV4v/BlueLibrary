package com.vang.userservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteUserCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String userId;
    private String avatar;
}