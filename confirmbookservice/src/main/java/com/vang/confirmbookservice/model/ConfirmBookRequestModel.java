package com.vang.confirmbookservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConfirmBookRequestModel implements Serializable {

    private String bookId;
    private int status;
}