package com.lec.spring.controller;

import com.lec.spring.entity.Recipe;
import com.lec.spring.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerRecipe(@RequestBody Recipe recipe) {

        // 작성일 저장
        recipe.setUploadOn(uploadedTime());

        return ResponseEntity.ok(recipeRepository.save(recipe));
    }

    @GetMapping("/search/{recipeName}")
    public ResponseEntity<?> searchRecipe(@PathVariable String name) {

        if (!recipeRepository.existsByName(name)) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(recipeRepository.findByName(name));
    }

    @PutMapping("/update/{recipeId}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe updatedRecipe) {
        Recipe originalRecipe = recipeRepository.findById(recipeId).get();
        if (originalRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        originalRecipe.setName(updatedRecipe.getName());

    return null;
    }

    @DeleteMapping("/delete/{recId}")
    public ResponseEntity deleteRec(@PathVariable Long recId) {
//        if (!recipes.containsKey(recId)) {
//            return ResponseEntity.ok("레시피를 찾지 못 하였습니다.");
//        }
//
//        recipes.remove(recId);
//        return ResponseEntity.ok("삭제 완료");
        return null;
    }


    // 작성일(수정일) 계산
    public String uploadedTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
