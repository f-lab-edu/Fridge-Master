package com.lec.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    private Long id;
    private String name;
    private String description;
    private String[] ingredient;
    private String imageUrl;
    private String uploadBy;
    private String uploadOn;
}
