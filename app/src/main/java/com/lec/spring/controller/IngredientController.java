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

    private static final Map<Long, Ingredient> ingredientStore = new ConcurrentHashMap<Long, Ingredient>();
    private static AtomicLong id = new AtomicLong(0);

    @PostMapping("/register")
    public ResponseEntity<?> registerIng(@RequestBody Ingredient ing) {

        ing.setId(id.incrementAndGet());
//      todo
//      기존 재료가 이미 등록된 재료인지 확인

        ingredientStore.put(ing.getId(), ing);
        return ResponseEntity.ok(ing);
    }

    @GetMapping("/search/{ingId}")
    public ResponseEntity<?> searchIng(@PathVariable Long ingId) {

        if (!ingredientStore.containsKey(ingId)) {
            return ResponseEntity.ok("유효하지 않은 ID입니다.");
        }

        return ResponseEntity.ok(ingredientStore.get(ingId));
    }

    @PutMapping("/update/{ingId}")
    public ResponseEntity<?> updateIng(@PathVariable Long ingId, @RequestBody Ingredient updatedIng) {

        Ingredient oldIng = ingredientStore.get(ingId);

        if (!ingredientStore.containsKey(ingId)) {
            return ResponseEntity.ok("유효하지 않은 ID입니다.");
//        } else if (ingredientStore.containsValue(updatedIng.getName())) {
//            return ResponseEntity.ok("이미 등록된 재료입니다.");
        } else {
            updatedIng.setId(ingId);
            ingredientStore.put(ingId, updatedIng);
        }


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
