package com.vang.bookservice.command.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class BookRequestModel implements Serializable {

    private String bookId;
    private String name;
    private String authorId;
    private String authorDetail;
    private String typeId;
    private String typeDetail;
    private String publisherId;
    private String publisherDetail;
    private String description;
    private String image;
    private int quantity;
    private int status;
    private String createdDate;
    private String lastModified;
    private MultipartFile imageData;
}