package com.lec.spring.service;

import com.lec.spring.entity.Recipe;
import com.lec.spring.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe recipe) {
        if(recipe == null) {
            return null;
        }
        return recipeRepository.save(recipe);
    }

    public List<Recipe> findByName(String name) {
        return recipeRepository.findByName(name);
    }
}
