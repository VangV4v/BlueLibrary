package com.vang.adminservice.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Builder
@Data
public class ResponseCommon implements Serializable {

    private String message;
    private boolean error = false;
    private Set<String> errorMessage;
}