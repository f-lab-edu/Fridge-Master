package com.lec.spring.repository;

import com.lec.spring.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.atomic.AtomicLong;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
