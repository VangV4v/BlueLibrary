package com.vang.typeservice.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeResponseModel implements Serializable {
    private String TypeId;
    private String TypeName;
    private String TypeDescription;
    private int CountOfBook;

    public void initialize() {
        TypeId = null;
        TypeName = null;
        TypeDescription = null;
        CountOfBook = 0;
    }
}