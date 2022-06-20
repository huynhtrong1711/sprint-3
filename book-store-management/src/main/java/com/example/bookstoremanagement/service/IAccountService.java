package com.example.bookstoremanagement.service;

import com.example.bookstoremanagement.model.Account;

import java.util.Optional;

public interface IAccountService {
    //lay user thong qua user name
    Optional<Account> findByUsername(String username);
    //kiem tra xem username da ton tai trong data hay chua(truowc khi tao moi)
    Boolean existsUserByUsername(String username);
    //kiem tra xem email co ton taij trong databasr hay chua
    Boolean existsByEmail(String email);
    Account save(Account account);
}
