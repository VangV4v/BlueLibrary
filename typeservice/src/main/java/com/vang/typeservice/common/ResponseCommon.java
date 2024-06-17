package com.vang.typeservice.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ResponseCommon implements Serializable {

    private String message;
    private boolean error = false;
    private Set<String> errorMessage;
}