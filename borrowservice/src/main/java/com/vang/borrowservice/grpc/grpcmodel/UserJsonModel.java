package com.vang.borrowservice.grpc.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserJsonModel implements Serializable {

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
}