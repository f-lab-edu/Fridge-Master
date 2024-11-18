package com.lec.spring.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    public static class Ingredient {

    }

    @PostMapping("/register")
    public String registerIng() {
        return "재료 등록 성공";
    }

    @GetMapping("/search/{ingId}")
    public Ingredient searchIng(@PathVariable Long ingId) {
        return null;
    }

    @PutMapping("/update/{ingId}")
    public Ingredient updateIng(@PathVariable Long ingId) {
        return null;
    }

    @DeleteMapping("/delete/{ingId}")
    public String deleteIng(@PathVariable Long ingId) {
        return "삭제 완료";
    }
}
