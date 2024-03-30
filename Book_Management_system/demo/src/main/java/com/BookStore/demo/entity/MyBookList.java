package com.BookStore.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MyBooks")
public class MyBookList {
    @Id
    private int id;
    private String name;
    private String author;
    private String price;





}
