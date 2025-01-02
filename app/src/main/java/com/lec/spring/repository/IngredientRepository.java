package com.lec.spring.repository;

import com.lec.spring.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.atomic.AtomicLong;

public interface IngredientRepository extends JpaRepository<Ingredient, AtomicLong> {
}
