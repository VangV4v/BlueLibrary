package com.vang.typeservice.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeRequestModel implements Serializable {

    private String TypeId;
    private String TypeName;
    private String TypeDescription;
    private int CountOfBook;
}