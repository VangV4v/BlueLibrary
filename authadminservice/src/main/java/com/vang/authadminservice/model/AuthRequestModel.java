package com.vang.authadminservice.model;

import lombok.Data;

@Data
public class AuthRequestModel {

    private String username;
    private String password;
}