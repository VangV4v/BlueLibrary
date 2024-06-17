package com.vang.typeservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Types")
@Data
public class Types {
    @Id
    @Column(name = "TypeId", length = 15)
    private String TypeId;
    @Column(name = "TypeName", length = 255)
    private String TypeName;
    @Column(name = "TypeDescription", length = 255)
    private String TypeDescription;
    @Column(name = "CountOfBook")
    private int CountOfBook;
}