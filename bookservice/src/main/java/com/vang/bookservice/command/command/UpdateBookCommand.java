package com.vang.bookservice.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UpdateBookCommand {

    @TargetAggregateIdentifier
    private Long generateAggregateId;
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
    private byte[] imageData;
    private String imageName;
}