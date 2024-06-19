package com.vang.authorservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Authors")
@Data
public class Authors {

    @Id
    @Column(name = "AuthorId")
    private String authorId;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "DateOfBirth")
    private String dateOfBirth;
    @Column(name = "Country")
    private String country;
    @Column(name = "CountOfBook")
    private int countOfBook;
}