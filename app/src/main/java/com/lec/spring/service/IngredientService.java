package com.lec.spring.service;

import com.lec.spring.entity.Ingredient;

import java.util.concurrent.atomic.AtomicLong;

public interface IngredientService {
    Ingredient registerIngredient(Ingredient ingredient);

    Ingredient updateIngredient(Ingredient ingredient);

    Ingredient deleteIngredient(Long id);

//    Ingredient getIng(Long id);
}
