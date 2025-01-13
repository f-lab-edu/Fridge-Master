package com.lec.spring.controller;


import com.lec.spring.entity.Ingredient;
import com.lec.spring.entity.User;
import com.lec.spring.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/register")
    public ResponseEntity<?> registerIngredient(@RequestBody Ingredient ing) {

        if (ing.getName() == null || ing.getName().trim().equals("")) {
            return ResponseEntity.badRequest().body("재료명이 입력되지 않았습니다.");
        }

        if (ingredientService.existIngredient(ing.getName())) {
            return ResponseEntity.badRequest().body("이미 등록된 재료입니다.");
        }

        ingredientService.registerIngredient(ing);

        return ResponseEntity.ok(ing);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchIng(@PathVariable Long id) {

        if (ingredientService.getIngredient(id) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredientService.getIngredient(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {

        if(ingredientService.getIngredient(id) == null) {
            return ResponseEntity.badRequest().body("유효하지 않은 ID입니다.");
        }


        Ingredient oldIngredient = ingredientService.getIngredient(id);
        System.out.println(oldIngredient.getName());
        if (ingredientService.existIngredient(updatedIngredient.getName())) {
            return ResponseEntity.badRequest().body("이미 등록된 재료입니다.");
        }

        updatedIngredient.setId(id);
        return ResponseEntity.ok(ingredientService.updateIngredient(updatedIngredient));
    }

    @DeleteMapping("/delete/{ingId}")
    public ResponseEntity<?> deleteIng(@PathVariable Long id) {
        if (ingredientService.getIngredient(id) == null) {
            return ResponseEntity.ok("유효하지 않은 ID입니다.");
        }

        Ingredient deletedIngredient = ingredientService.getIngredient(id);
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(deletedIngredient);
    }
}
