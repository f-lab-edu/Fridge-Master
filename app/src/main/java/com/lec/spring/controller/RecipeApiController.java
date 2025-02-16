package com.lec.spring.controller;


import com.lec.spring.repository.RecipeRepository;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class RecipeApiController {
    // https://openapi.foodsafetykorea.go.kr/api/e914980e7cc34724a74b/COOKRCP01/json/1001/2000
    private RecipeRepository recipeRepository;

    @Value("${recipe.api.key}")
    private String apikey;

    String result = "";

    @GetMapping("/{startIndex}/{endIndex}")
    public void saveRecipe(@PathVariable int startIndex, @PathVariable int endIndex) {
        String apiurl = String.format("https://openapi.foodsafetykorea.go.kr/api/%s/COOKRCP01/json/%d/%d", apikey, startIndex, endIndex);
        try {
            URL url = new URL(apiurl);
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = br.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray recipes = (JSONArray) jsonObject.get("row");

            for (int i = 0; i < recipes.size(); i++) {
                JSONObject recipe = (JSONObject) recipes.get(i);
                String ingredients = (String) recipe.get("RCP_PARTS_DTLS");
                String recipeWay = (String) recipe.get("RCP_WAY2");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
