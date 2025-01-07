package com.lec.spring.service;

import com.lec.spring.entity.Ingredient;
import com.lec.spring.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient registerIngredient(Ingredient ingredient) {
        if(ingredient != null) {
            return ingredientRepository.save(ingredient);
        }
        return null;
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient updateIng = ingredientRepository.findById(ingredient.getId()).orElse(null);
        if(updateIng == null) return null;

        updateIng.setName(ingredient.getName());

        return ingredientRepository.save(updateIng);
    }

    @Override
    public Ingredient deleteIngredient(Long id) {
        boolean exists = ingredientRepository.existsById(id);
        if(!exists) return null;

        Ingredient deletedIngredient = ingredientRepository.findById(id).orElse(null);
        if(deletedIngredient == null) return null;

        ingredientRepository.deleteById(id);
        return deletedIngredient;
    }

//    @Override
//    public Ingredient getIng(Long id) {
//        Ingredient ing = ingredientRepository.findById(id);
//        return null;
//    }
}
