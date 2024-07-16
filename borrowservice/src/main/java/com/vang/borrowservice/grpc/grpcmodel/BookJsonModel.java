package com.vang.borrowservice.grpc.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookJsonModel implements Serializable {

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
}