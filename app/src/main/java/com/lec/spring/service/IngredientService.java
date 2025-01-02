package com.lec.spring.service;

import com.lec.spring.entity.Ingredient;

import java.util.concurrent.atomic.AtomicLong;

public interface IngredientService {
    int registerIng(Ingredient ingredient);

    int updateIng(Ingredient ingredient);

    int deleteIng(AtomicLong id);

    Ingredient getIng(AtomicLong id);
}
