package com.vang.confirmbookservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeJsonModel implements Serializable {

    private String TypeId;
    private String TypeName;
    private String TypeDescription;
    private int CountOfBook;
}