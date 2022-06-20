package com.example.bookstoremanagement.security.userprinciple;

import com.example.bookstoremanagement.model.Account;
import com.example.bookstoremanagement.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    IAccountRepository iAccountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found username or password"+ username));
        return AccountPrinciple.build(account);
    }
}
