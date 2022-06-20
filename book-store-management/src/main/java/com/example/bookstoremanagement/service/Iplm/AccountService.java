package com.example.bookstoremanagement.service.Iplm;

import com.example.bookstoremanagement.model.Account;
import com.example.bookstoremanagement.repository.IAccountRepository;
import com.example.bookstoremanagement.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public Boolean existsUserByUsername(String username) {
        return iAccountRepository.existsUserByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iAccountRepository.existsByEmail(email);
    }

    @Override
    public Account save(Account account) {
        return iAccountRepository.save(account);
    }
}
