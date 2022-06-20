package com.example.bookstoremanagement.model;

import com.example.bookstoremanagement.repository.ICategoryRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Roles  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RolesName name;
    @ManyToMany(mappedBy = "rolesSet")
    @JsonBackReference
    private Set<Account> accountSet;

}
