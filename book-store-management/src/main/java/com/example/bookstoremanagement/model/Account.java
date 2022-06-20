package com.example.bookstoremanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(min=3,max=50)
    private String name;
    @Size(min=3,max=50)
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    @Lob
    private String avatar;
    private Boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="account_roles",
                joinColumns = @JoinColumn(name = "account_id"),
                inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Roles> rolesSet;



    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private Customer customer;


    public Account(String name, String username, String email, String encode) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = encode;
    }
}
