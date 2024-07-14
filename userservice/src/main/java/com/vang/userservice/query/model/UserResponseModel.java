package com.vang.userservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseModel implements Serializable {

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
    private int countOfExpired;
    private int activeStatus;

    public void initialize() {

        this.userId = null;
        this.firstName = null;
        this.lastName = null;
        this.dateOfBirth = null;
        this.username = null;
        this.email = null;
        this.phone = null;
        this.password = null;
        this.type = 0;
        this.createdDate = null;
        this.lastModified = null;
        this.role = null;
        this.avatar = null;
        this.countOfExpired = 0;
        this.activeStatus = 0;
    }
}