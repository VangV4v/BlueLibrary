package com.vang.confirmbookservice.common;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class ConfirmResponseCommon {

    private boolean isSuccess;
    private String message;
    private boolean isError = false;
    private Set<String> errors;
}