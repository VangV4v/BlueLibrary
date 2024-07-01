package com.vang.userservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
    private String userId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String username;
    private String email;
    private String phone;
    private String password;
    private int type;
    private String createdDate;
    private String lastModified;
    private String role;
    private String avatar;
    private int activeStatus;
    private byte[] imageData;
}