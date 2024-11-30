package com.lec.spring.controller;

import com.lec.spring.entity.Recipe;
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


    private static final Map<Long, Recipe> recipes = new ConcurrentHashMap<>();
    private static AtomicLong id = new AtomicLong(0);

    @PostMapping("/register")
    public ResponseEntity<?> registerRec(@RequestBody Recipe recipe) {

        recipe.setId(id.incrementAndGet());

        // 작성일 저장
        recipe.setUploadOn(uploadedTime());

        /*
          작성자 확인
         */
        // todo

        recipes.put(recipe.getId(), recipe);

        return ResponseEntity.ok(recipes.get(recipe.getId()));
    }

    @GetMapping("/search/{recId}")
    public ResponseEntity<?> searchRec(@PathVariable Long recId){

        if (!recipes.containsKey(recId)) {
            return ResponseEntity.ok("레시피를 찾지 못 하였습니다.");
        }

        return ResponseEntity.ok(recipes.get(recId));
    }

    @PutMapping("/update/{recId}")
    public ResponseEntity<?> updateRec(@PathVariable Long recId, @RequestBody Recipe updatedRec) {
        if (!recipes.containsKey(recId)) {
            return ResponseEntity.ok("레시피를 찾지 못 하였습니다.");
        }

        updatedRec.setId(recId);
        updatedRec.setUploadOn(uploadedTime());

        recipes.put(recId, updatedRec);

        return ResponseEntity.ok(recipes.get(recId));
    }

    @DeleteMapping("/delete/{recId}")
    public ResponseEntity deleteRec(@PathVariable Long recId) {
        if (!recipes.containsKey(recId)) {
            return ResponseEntity.ok("레시피를 찾지 못 하였습니다.");
        }

        recipes.remove(recId);
        return ResponseEntity.ok("삭제 완료");
    }


    // 작성일(수정일) 계산
    public String uploadedTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
