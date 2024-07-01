package com.vang.userservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestModel implements Serializable {

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
}