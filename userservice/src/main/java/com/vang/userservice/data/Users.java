package com.vang.userservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class Users {

    @Id
    @Column(name = "UserId")
    private String userId;
    @Column(name = "Firstname")
    private String firstName;
    @Column(name = "Lastname")
    private String lastName;
    @Column(name = "DateOfBirth")
    private String dateOfBirth;
    @Column(name = "username")
    private String username;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Password")
    private String password;
    @Column(name = "Type")
    private int type;
    @Column(name = "CreatedDate")
    private String createdDate;
    @Column(name = "LastModified")
    private String lastModified;
    @Column(name = "Role")
    private String role;
    @Column(name = "Avatar")
    private String avatar;
    @Column(name = "ActiveStatus")
    private int activeStatus;
}