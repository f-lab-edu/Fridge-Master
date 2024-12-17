package com.lec.spring.repository;

import com.lec.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.atomic.AtomicLong;

public interface AccountRepository extends JpaRepository<User, AtomicLong> {
}
