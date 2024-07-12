package com.vang.authuserservice.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class AuthenticateResponseCommon implements Serializable {

    private String jwt = null;
    private String role = null;
    private String expiration = null;
    private boolean isAuthenticated = false;
}