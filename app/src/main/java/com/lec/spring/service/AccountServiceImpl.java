package com.lec.spring.service;

import com.lec.spring.entity.User;
import com.lec.spring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.atomic.AtomicLong;

public class AccountServiceImpl implements AccountService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public int join(User user) {
        int result = 0;
        user.setUsername(user.getUsername().toUpperCase());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        accountRepository.save(user);
        return 1;
    }

    @Override
    public User detail(Long id) {
        accountRepository.findById(id);
        return null;
    }

    @Override
    public int update(User user) {
        return 1;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

}
