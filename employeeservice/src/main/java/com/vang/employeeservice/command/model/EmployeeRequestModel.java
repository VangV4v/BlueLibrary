package com.vang.employeeservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeRequestModel implements Serializable {

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