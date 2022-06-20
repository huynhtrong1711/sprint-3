package com.example.bookstoremanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String img;
    private String code;
    private String author;
    private String translator;
    private String producer;
    private String size;
    private int pages;
    @Column(columnDefinition = "DATE")
    private String date;
    private Long quantity;
    private Long rest;
    private Double price;
    private Long promotion;
    private Boolean delFlag;
    @ManyToOne
    private Category category;

    @JsonBackReference
    @OneToMany(mappedBy = "book")
    private Set<Order> orderSet;
}
