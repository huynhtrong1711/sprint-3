package com.example.bookstoremanagement.service;

import com.example.bookstoremanagement.model.Roles;
import com.example.bookstoremanagement.model.RolesName;

import java.util.Optional;

public interface IRolesService {
    Optional<Roles> findByName(RolesName name);
}
