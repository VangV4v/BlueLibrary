package com.vang.adminservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminResponseModel implements Serializable {

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

    public void initialize() {

        this.adminId = null;
        this.firstName = null;
        this.lastName = null;
        this.dateOfBirth = null;
        this.username = null;
        this.email = null;
        this.phone = null;
        this.password = null;
        this.createdDate = null;
        this.lastModified = null;
        this.role = null;
        this.activeStatus = 0;
    }

}