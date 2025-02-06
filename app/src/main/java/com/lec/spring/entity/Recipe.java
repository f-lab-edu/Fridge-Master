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

// 참고 https://www.foodsafetykorea.go.kr/api/openApiInfo.do?menu_grp=MENU_GRP31&menu_no=661&show_cnt=10&start_idx=1&svc_no=COOKRCP01
public class Recipe {

    @Id
    private Long id;
    private String name;

    private String recipeWay;   // ex) 끓이기, 찌기 등
    private String recipeCategory; // ex) 밥, 반찬, 국/찌개, 후식 등
    private String hashtag;


    private String recipeImage;

    private String ingredients;

    private String manual01;
    private String manual02;
    private String manual03;
    private String manual04;
    private String manual05;
    private String manual06;
    private String manual07;
    private String manual08;
    private String manual09;
    private String manual10;
    private String manual11;
    private String manual12;
    private String manual13;
    private String manual14;
    private String manual15;
    private String manual16;
    private String manual17;
    private String manual18;
    private String manual19;
    private String manual20;

    private String manual01Image;
    private String manual02Image;
    private String manual03Image;
    private String manual04Image;
    private String manual05Image;
    private String manual06Image;
    private String manual07Image;
    private String manual08Image;
    private String manual09Image;
    private String manual10Image;
    private String manual11Image;
    private String manual12Image;
    private String manual13Image;
    private String manual14Image;
    private String manual15Image;
    private String manual16Image;
    private String manual17Image;
    private String manual18Image;
    private String manual19Image;
    private String manual20Image;

    private String imageUrl;
    private String uploadBy;    // 작성자
    private String uploadOn;    // 작성일
}
