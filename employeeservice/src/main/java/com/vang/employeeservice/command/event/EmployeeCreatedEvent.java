package com.vang.employeeservice.command.event;

import lombok.Data;

@Data
public class EmployeeCreatedEvent {

    private Long generateAggregateId;
    private String employeeId;
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
    private String avatar;
    private int activeStatus;
}