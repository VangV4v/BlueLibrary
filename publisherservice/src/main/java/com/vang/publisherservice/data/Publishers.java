package com.vang.publisherservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Publishers")
@Data
public class Publishers {

    @Id
    @Column(name = "PublisherId", length = 15)
    private String publisherId;
    @Column(name = "Name", length = 255)
    private String name;
    @Column(name = "Description", length = 255)
    private String description;
    @Column(name = "Address", length = 255)
    private String address;
    @Column(name = "CountOfBook")
    private int countOfBook;
}