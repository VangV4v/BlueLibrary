package com.vang.adminservice.command.event;

import lombok.Data;

@Data
public class AdminCreatedEvent {

    private Long generateAggregateId;
    private String adminId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String createdDate;
    private String lastModified;
    private String role;
    private int activeStatus;
}