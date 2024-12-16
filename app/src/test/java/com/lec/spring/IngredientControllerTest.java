package com.lec.spring;

import com.lec.spring.entity.Ingredient;
import com.lec.spring.entity.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngredientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test_Success() throws Exception {
        Ingredient ing = new Ingredient();
        ing.setName("풋고추");

        ResponseEntity<Ingredient> response = testRestTemplate.exchange("/ingredient/register", HttpMethod.POST, new HttpEntity<>(ing), Ingredient.class);
        assertNotNull(response);
        System.out.println("/ingredient/register");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        System.out.println("-".repeat(20));
        System.out.println("/ingredient/update/1");
        ing.setName("청양고추");
        response = testRestTemplate.exchange("/ingredient/update/1", HttpMethod.PUT, new HttpEntity<>(ing), Ingredient.class);
        assertNotNull(response);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        System.out.println("-".repeat(20));
        System.out.println("/ingredient/search/1");
        response = testRestTemplate.exchange("/ingredient/search/1", HttpMethod.GET, new HttpEntity<>(null), Ingredient.class);
        assertNotNull(response);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        System.out.println("-".repeat(20));
        System.out.println("/ingredient/delete/1");
        ResponseEntity<String> del_response = testRestTemplate.exchange("/ingredient/delete/1", HttpMethod.DELETE, null, String.class);
        assertNotNull(del_response);
        System.out.println(del_response.getStatusCode());
        System.out.println(del_response.getBody());
    }


    @Test
    public void test_fail() throws Exception {
        Ingredient ing = new Ingredient();
        HttpEntity<Ingredient> null_request = new HttpEntity<>(ing);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/ingredient/register", null_request, String.class);
        assertNotNull(response);
        System.out.println("/ingredient/register 재료명 입력하지 않은 경우");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        ing.setName("풋고추");
        testRestTemplate.postForEntity("/ingredient/register", new HttpEntity<>(ing), Ingredient.class);
        response = testRestTemplate.postForEntity("/ingredient/register", new HttpEntity<>(ing), String.class);
        System.out.println("-".repeat(20));
        System.out.println("/ingredient/register 재료명 중복");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        System.out.println("-".repeat(20));
        System.out.println("/ingredient/update/2 유효하지 않은 ID");
        response = testRestTemplate.exchange("/ingredient/update/2", HttpMethod.PUT, null_request, String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        System.out.println("-".repeat(20));
        System.out.println("/ingredient/update/2 이미 등록된 재료");
        ing.setName("청양고추");
        testRestTemplate.exchange("/ingredient/register", HttpMethod.POST, new HttpEntity<>(ing), Ingredient.class);


        ing.setName("풋고추");
        ResponseEntity<String> response2 = testRestTemplate.exchange("/ingredient/update/2", HttpMethod.PUT, new HttpEntity<>(ing), String.class);
        assertNotNull(response2);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }
}
