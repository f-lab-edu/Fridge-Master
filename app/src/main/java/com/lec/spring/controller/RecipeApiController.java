package com.lec.spring.controller;


import com.lec.spring.entity.Recipe;
import com.lec.spring.repository.RecipeRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class RecipeApiController {
    // https://openapi.foodsafetykorea.go.kr/api/e914980e7cc34724a74b/COOKRCP01/json/1001/2000

    @Autowired
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
            JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
            JSONArray recipes = (JSONArray) cookRcp01.get("row");

//            System.out.println(recipes.size());

            for (int i = 0; i < recipes.size(); i++) {
                JSONObject data = (JSONObject) recipes.get(i);
                String ingredients = (String) data.get("RCP_PARTS_DTLS");
                String recipeWay = (String) data.get("RCP_WAY2");
                String recipeName = (String) data.get("RCP_NM");
                String recipeTip = (String) data.get("RCP_NA_TIP");
                String recipeType = (String) data.get("RCP_PAT2");
                String hashtag = (String) data.get("HASH_TAG");


                String step1 = (String) data.get("MANUAL01");
                String step2 = (String) data.get("MANUAL02");
                String step3 = (String) data.get("MANUAL03");
                String step4 = (String) data.get("MANUAL04");
                String step5 = (String) data.get("MANUAL05");
                String step6 = (String) data.get("MANUAL06");
                String step7 = (String) data.get("MANUAL07");
                String step8 = (String) data.get("MANUAL08");
                String step9 = (String) data.get("MANUAL09");
                String step10 = (String) data.get("MANUAL10");
                String step11 = (String) data.get("MANUAL11");
                String step12 = (String) data.get("MANUAL12");
                String step13 = (String) data.get("MANUAL13");
                String step14 = (String) data.get("MANUAL14");
                String step15 = (String) data.get("MANUAL15");
                String step16 = (String) data.get("MANUAL16");
                String step17 = (String) data.get("MANUAL17");
                String step18 = (String) data.get("MANUAL18");
                String step19 = (String) data.get("MANUAL19");
                String step20 = (String) data.get("MANUAL20");

                String mainImageUrl = (String) data.get("ATT_FILE_NO_MAIN");
                String recipeMarkImageUrl = (String) data.get("ATT_FILE_NO_MK");

                String step1ImageUrl = (String) data.get("MANUAL_IMG01");
                String step2ImageUrl = (String) data.get("MANUAL_IMG02");
                String step3ImageUrl = (String) data.get("MANUAL_IMG03");
                String step4ImageUrl = (String) data.get("MANUAL_IMG04");
                String step5ImageUrl = (String) data.get("MANUAL_IMG05");
                String step6ImageUrl = (String) data.get("MANUAL_IMG06");
                String step7ImageUrl = (String) data.get("MANUAL_IMG07");
                String step8ImageUrl = (String) data.get("MANUAL_IMG08");
                String step9ImageUrl = (String) data.get("MANUAL_IMG09");
                String step10ImageUrl = (String) data.get("MANUAL_IMG10");
                String step11ImageUrl = (String) data.get("MANUAL_IMG11");
                String step12ImageUrl = (String) data.get("MANUAL_IMG12");
                String step13ImageUrl = (String) data.get("MANUAL_IMG13");
                String step14ImageUrl = (String) data.get("MANUAL_IMG14");
                String step15ImageUrl = (String) data.get("MANUAL_IMG15");
                String step16ImageUrl = (String) data.get("MANUAL_IMG16");
                String step17ImageUrl = (String) data.get("MANUAL_IMG17");
                String step18ImageUrl = (String) data.get("MANUAL_IMG18");
                String step19ImageUrl = (String) data.get("MANUAL_IMG19");
                String step20ImageUrl = (String) data.get("MANUAL_IMG20");

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


                Recipe recipe = Recipe.builder()
                        .name(recipeName)
                        .ingredients(ingredients)
                        .recipeWay(recipeWay)
                        .recipeTip(recipeTip)
                        .hashtag(hashtag)
                        .recipeType(recipeType)
                        .manual01(step1)
                        .manual02(step2)
                        .manual03(step3)
                        .manual04(step4)
                        .manual05(step5)
                        .manual06(step6)
                        .manual07(step7)
                        .manual08(step8)
                        .manual09(step9)
                        .manual10(step10)
                        .manual11(step11)
                        .manual12(step12)
                        .manual13(step13)
                        .manual14(step14)
                        .manual15(step15)
                        .manual16(step16)
                        .manual17(step17)
                        .manual18(step18)
                        .manual19(step19)
                        .manual20(step20)
                        .originImage(recipeMarkImageUrl)
                        .mainImage(mainImageUrl)
                        .manual01Image(step1ImageUrl)
                        .manual02Image(step2ImageUrl)
                        .manual03Image(step3ImageUrl)
                        .manual04Image(step4ImageUrl)
                        .manual05Image(step5ImageUrl)
                        .manual06Image(step6ImageUrl)
                        .manual07Image(step7ImageUrl)
                        .manual08Image(step8ImageUrl)
                        .manual09Image(step9ImageUrl)
                        .manual10Image(step10ImageUrl)
                        .manual11Image(step11ImageUrl)
                        .manual12Image(step12ImageUrl)
                        .manual13Image(step13ImageUrl)
                        .manual14Image(step14ImageUrl)
                        .manual15Image(step15ImageUrl)
                        .manual16Image(step16ImageUrl)
                        .manual17Image(step17ImageUrl)
                        .manual18Image(step18ImageUrl)
                        .manual19Image(step19ImageUrl)
                        .manual20Image(step20ImageUrl)
                        .uploadBy("administrator")
                        .uploadOn(now.format(formatter))
                        .build();
//                System.out.println(recipe);
                recipeRepository.save(recipe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
