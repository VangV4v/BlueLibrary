package com.vang.userservice.command.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UserDeletedEvent {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String userId;
    private String avatar;
}