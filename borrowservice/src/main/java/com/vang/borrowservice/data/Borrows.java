package com.vang.borrowservice.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Borrows")
@Data
public class Borrows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BorrowId")
    private int borrowId;
    @Column(name = "CreatedDate")
    private String createdDate;
    @Column(name = "LastModified")
    private String lastModified;
    @Column(name = "ReturnDate")
    private String returnDate;
    @Column(name = "BookId")
    private String bookId;
    @Column(name = "BookDetail", length = 2000)
    private String bookDetail;
    @Column(name = "UserId")
    private String userId;
    @Column(name = "UserDetail", length = 2000)
    private String userDetail;
    @Column(name = "ConfirmStatus")
    private int confirmStatus;
}