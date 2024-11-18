package com.lec.spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    public static class Recipe {

    }

    @PostMapping("/register")
    public String registerRec() {
        return "레시피 등록 성공.";
    }

    @GetMapping("/search/{recId}")
    public Recipe searchRec(@PathVariable Long recId){
        return null;
    }

    @PutMapping("/update/{recId}")
    public Recipe updateRec(@PathVariable Long recId) {
        return null;
    }

    @DeleteMapping("/delete/{recId}")
    public String deleteRec(@PathVariable Long recId) {
        return "삭제 완료";
    }
}
