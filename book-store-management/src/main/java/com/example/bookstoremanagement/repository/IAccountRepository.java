package com.example.bookstoremanagement.repository;

import com.example.bookstoremanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    //lay user thong qua user name
    Optional<Account> findByUsername(String username);
    //kiem tra xem username da ton tai trong data hay chua(truowc khi tao moi)
    Boolean existsUserByUsername(String username);
    //kiem tra xem email co ton taij trong databasr hay chua
    Boolean existsByEmail(String email);

}
