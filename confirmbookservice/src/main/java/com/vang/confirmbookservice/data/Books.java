package com.vang.confirmbookservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Books")
@Data
public class Books {

    @Id
    @Column(name = "BookId")
    private String bookId;
    @Column(name = "Name")
    private String name;
    @Column(name = "AuthorId")
    private String authorId;
    @Column(name = "AuthorDetail", length = 2000)
    private String authorDetail;
    @Column(name = "TypeId")
    private String typeId;
    @Column(name = "TypeDetail", length = 2000)
    private String typeDetail;
    @Column(name = "PublisherId")
    private String publisherId;
    @Column(name = "PublisherDetail", length = 2000)
    private String publisherDetail;
    @Column(name = "Description")
    private String description;
    @Column(name = "Image")
    private String image;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Status")
    private int status;
    @Column(name = "CreatedDate")
    private String createdDate;
    @Column(name = "LastModified")
    private String lastModified;
}