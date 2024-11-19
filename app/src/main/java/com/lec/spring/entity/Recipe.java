package com.lec.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    private Long id;
    private String name;
    private String steps;
    private Map<Ingredient, Integer> ingredients;
    private String imageUrl;
    private String uploadBy;    // 작성자
    private String uploadOn;    // 작성일
}
