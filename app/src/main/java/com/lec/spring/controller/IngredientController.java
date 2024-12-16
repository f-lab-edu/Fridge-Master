package com.lec.spring.controller;


import com.lec.spring.entity.Ingredient;
import com.lec.spring.entity.User;
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

    private final Map<Long, Ingredient> ingredientStore = new ConcurrentHashMap<Long, Ingredient>();
    private AtomicLong id = new AtomicLong(0);

    // todo
    // Response 공통 응답 구조

    @PostMapping("/register")
    public ResponseEntity<?> registerIng(@RequestBody Ingredient ing) {

        if (ing.getName() == null || ing.getName().trim().equals("")) {
            return ResponseEntity.badRequest().body("재료명이 입력되지 않았습니다.");
        }
        boolean isDuplicate = ingredientStore.values().stream()
                .anyMatch(existingIng -> existingIng.getName().equalsIgnoreCase(ing.getName()));

        if (isDuplicate) {
            return ResponseEntity.badRequest().body("이미 등록된 재료입니다.");
        }

        ing.setId(id.incrementAndGet());

        ingredientStore.put(ing.getId(), ing);
        return ResponseEntity.ok(ing);
    }

    @GetMapping("/search/{ingId}")
    public ResponseEntity<?> searchIng(@PathVariable Long ingId) {

        if (!ingredientStore.containsKey(ingId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredientStore.get(ingId));
    }

    @PutMapping("/update/{ingId}")
    public ResponseEntity<?> updateIng(@PathVariable Long ingId, @RequestBody Ingredient updatedIng) {


        if (ingredientStore.containsKey(ingId) == false) {
            return ResponseEntity.badRequest().body("유효하지 않은 ID입니다.");
        }

        Ingredient oldIng = ingredientStore.get(ingId);

        boolean isDuplicate = ingredientStore.values().stream()
                .anyMatch(existingIng -> existingIng.getName().equalsIgnoreCase(updatedIng.getName()));

        if (isDuplicate) {
            return ResponseEntity.badRequest().body("이미 등록된 재료입니다.");
        }

        updatedIng.setId(ingId);
        ingredientStore.put(ingId, updatedIng);


        return ResponseEntity.ok(ingredientStore.get(ingId));
    }

    @DeleteMapping("/delete/{ingId}")
    public ResponseEntity<?> deleteIng(@PathVariable Long ingId) {
        if (!ingredientStore.containsKey(ingId)) {
            return ResponseEntity.ok("유효하지 않은 ID입니다.");
        }

        ingredientStore.remove(ingId);
        return ResponseEntity.ok("삭제 완료");
    }
}
