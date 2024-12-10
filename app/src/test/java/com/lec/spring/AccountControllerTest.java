package com.lec.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.controller.AccountController;
import com.lec.spring.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// integration test 로 작성

// integration test 와 unit test
// 내가 테스트하고자 하는 것이 하나인가 두 개 이상인가

// @WebMvcTest // 컨트롤러와 관련된 빈만 로드한다. 가볍고 빠름.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 전체 애플리케이션 컨텍스트를 로드합니다. 더 많은 리소스를 사용하지만 통합 테스트에 적합.
//@AutoConfigureMockMvc

// api 에 대한 spec test
public class AccountControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private TestRestTemplate testRestTemplate;


    // 회원가입 성공 / 회원정보 수정 / 회원 탈퇴
    @Test
    public void testJoinUser_success() throws Exception {
        User testUser = User.builder()
                .username("kkamjang")
                .nickname("깜장거북")
                .password("1592")
                .email("mrg@naver.com")
                .build();

        ResponseEntity<User> response = testRestTemplate.exchange("/account/joinUser", HttpMethod.POST, new HttpEntity<>(testUser), User.class);
        assertNotNull(response);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());




        // 수정하기 test
        testUser.setNickname("깜장");
        response = testRestTemplate.exchange("/account/update/1",HttpMethod.PUT, new HttpEntity<>(testUser), User.class);
        assertNotNull(response);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        ResponseEntity<String> delResponse = testRestTemplate.exchange("/account/delete/1",HttpMethod.DELETE, new HttpEntity<>(testUser), String.class);
        assertNotNull(delResponse);
        System.out.println(delResponse.getStatusCode());
        System.out.println(delResponse.getBody());
    }

    @Test
    public void testJoinUser_failed() throws Exception {
        User testUser = new User();
        HttpEntity<User> request = new HttpEntity<>(testUser);

        ResponseEntity<String> response = testRestTemplate.exchange("/account/joinUser", HttpMethod.POST, request, String.class);
        assertNotNull(response);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }


    @Test
    public void getAccount() throws Exception {

        ResponseEntity<User> response = testRestTemplate.exchange("/account/info/1", HttpMethod.GET, null, User.class);
//        response.getStatusCode()
                // assert

        // Then: 응답 검증

    }

}
