package com.lec.spring.service;

import com.lec.spring.entity.User;
import com.lec.spring.repository.AccountRepository;

import java.util.concurrent.atomic.AtomicLong;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public int join(User user) {
        return accountRepository.save(user);
    }

    @Override
    public User detail(AtomicLong id) {
        return accountRepository.findById(id);
    }

    @Override
    public int update(User user) {
        return accountRepository.update(user);
    }

    @Override
    public int delete(AtomicLong id) {
        int result = 0;
        User user = accountRepository.findById(id);

        if (user != null) {
            result = accountRepository.delete(user);
        }
        return result;
    }
}
