package com.vang.adminservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRequestModel implements Serializable {

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