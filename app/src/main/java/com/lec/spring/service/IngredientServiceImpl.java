package com.lec.spring.service;

import com.lec.spring.entity.Ingredient;
import com.lec.spring.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public int registerIng(Ingredient ingredient) {
        int result = 0;
        if(ingredient != null) {
            ingredientRepository.save(ingredient);
            result = 1;
        }
        return result;
    }

    @Override
    public int updateIng(Ingredient ingredient) {
        Ingredient updateIng = ingredientRepository.findById(ingredient.getId()).orElse(null);
        if(updateIng == null) return 0;

        updateIng.setName(ingredient.getName());
        ingredientRepository.save(updateIng);

        return 1;
    }

    @Override
    public int deleteIng(Long id) {
        boolean exists = ingredientRepository.existsById(id);
        if(!exists) return 0;

        ingredientRepository.deleteById(id);
        return 1;
    }

//    @Override
//    public Ingredient getIng(Long id) {
//        Ingredient ing = ingredientRepository.findById(id);
//        return null;
//    }
}
