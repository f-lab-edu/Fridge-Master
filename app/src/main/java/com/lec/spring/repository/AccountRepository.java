package com.lec.spring.repository;

import com.lec.spring.entity.User;

import java.util.concurrent.atomic.AtomicLong;

public interface AccountRepository {
    int save(User user);

    User findByUsername(String username);

    User findById(AtomicLong id);

    int update(User user);

    int delete(User user);
}
