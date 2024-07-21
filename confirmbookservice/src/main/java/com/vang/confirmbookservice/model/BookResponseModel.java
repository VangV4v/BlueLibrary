package com.vang.confirmbookservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookResponseModel implements Serializable {

    private String bookId;
    private String name;
    private String authorId;
    private AuthorJsonModel authorDetail;
    private String typeId;
    private TypeJsonModel typeDetail;
    private String publisherId;
    private PublisherJsonModel publisherDetail;
    private String description;
    private String image;
    private int quantity;
    private int status;
    private String createdDate;
    private String lastModified;

    public void initialize() {

        this.bookId = null;
        this.name = null;
        this.authorId = null;
        this.authorDetail = null;
        this.typeId = null;
        this.typeDetail = null;
        this.publisherId = null;
        this.publisherDetail = null;
        this.description = null;
        this.image = null;
        this.quantity = 0;
        this.status = 0;
        this.createdDate = null;
        this.lastModified = null;
    }
}