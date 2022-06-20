package com.example.bookstoremanagement.repository;
import com.example.bookstoremanagement.model.Roles;
import com.example.bookstoremanagement.model.RolesName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(RolesName roleName);

}
