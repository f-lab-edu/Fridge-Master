package com.lec.spring.controller;

import com.lec.spring.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecipeApiController {
    // https://openapi.foodsafetykorea.go.kr/api/e914980e7cc34724a74b/COOKRCP01/json/1001/2000
    private RecipeRepository recipeRepository;

    @Value("${recipe.api.key}")
    private String apikey;

    String apiurl = "https://openapi.foodsafetykorea.go.kr/api/" + apikey;

    @GetMapping("/{startIndex}/{endIndex}")
    public void saveRecipe(@PathVariable int startIndex, @PathVariable int endIndex) {

    }


}
