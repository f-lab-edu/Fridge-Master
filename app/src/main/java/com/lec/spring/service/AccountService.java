package com.lec.spring.service;

import com.lec.spring.entity.User;

import java.util.concurrent.atomic.AtomicLong;

public interface AccountService {
    int join(User user);

    User detail(AtomicLong id);

    int update(User user);

    int delete(AtomicLong id);
}
