package com.vang.borrowservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BorrowRequestModel implements Serializable {

    private int borrowId;
    private String createdDate;
    private String lastModified;
    private String returnDate;
    private String bookId;
    private String bookDetail;
    private String userId;
    private String userDetail;
    private int confirmStatus;
    private int borrowTime;
}