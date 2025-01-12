package com.lec.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Recipe {

    @Id
    private Long id;
    private String name;
//    private String[] steps;
//    private Map<String, String> ingredients;
    private String imageUrl;
    private String uploadBy;    // 작성자
    private String uploadOn;    // 작성일
}
