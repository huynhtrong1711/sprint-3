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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String name;
    private String address;
    private String phone;
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private Set<Order> orderSet;

    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
}
