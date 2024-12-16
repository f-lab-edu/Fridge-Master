package com.lec.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.controller.AccountController;
import com.lec.spring.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    @Autowired
//    private ObjectMapper objectMapper;


    @Autowired
    private TestRestTemplate testRestTemplate;

    private final Logger log = LoggerFactory.getLogger(getClass());


    // 회원가입 성공 / 회원정보 수정 / 회원 탈퇴
    // 세부적으로 분류할 것
    @Test
    public void test_success() throws Exception {
        User testUser = User.builder()
                .username("kkamjang")
                .nickname("깜장거북")
                .password("1592")
                .email("mrg@naver.com")
                .build();

        // 회원가입 test
        ResponseEntity<User> response = testRestTemplate.exchange("/account/joinUser", HttpMethod.POST, new HttpEntity<>(testUser), User.class);
        assertNotNull(response);
        System.out.println("/account/joinUser");
        log.info(response.getStatusCode() + " : " + response.getBody());

        System.out.println("-".repeat(50));
        // 회원정보 조회 test 성공
        System.out.println("/account/info/1");
        response = testRestTemplate.exchange("/account/info/1", HttpMethod.GET, new HttpEntity<>(null), User.class);
        assertNotNull(response);
        log.info(response.getStatusCode() + " : " + response.getBody());



        System.out.println("-".repeat(50));
        // 삭제하기 test
        System.out.println("/account/delete/1");
        ResponseEntity<String> delResponse = testRestTemplate.exchange("/account/delete/1",HttpMethod.DELETE, new HttpEntity<>(testUser), String.class);
        assertNotNull(delResponse);
        log.info(delResponse.getStatusCode() + " : " + delResponse.getBody());
    }

    @Test
    public void update_test() throws Exception {
        User testUser = User.builder()
                .username("kkamjang")
                .nickname("깜장거북")
                .password("1592")
                .email("mrg@naver.com")
                .build();
        HttpEntity<User> request = new HttpEntity<>(testUser);
        testRestTemplate.exchange("/account/joinUser", HttpMethod.POST, request, User.class);
        ResponseEntity<User> response = testRestTemplate.exchange("/account/info/1", HttpMethod.GET, null, User.class);
        System.out.println("변경 전");
        log.info(response.getStatusCode() + " : " + response.getBody());
        testUser.setNickname("깜장");
        response = testRestTemplate.exchange("/account/update/1",HttpMethod.PUT, new HttpEntity<>(testUser), User.class);
        assertNotNull(response);
        System.out.println("변경 후");
        log.info(response.getStatusCode() + " : " + response.getBody());
    }

    @Test
    public void test_failed() throws Exception {
        User testUser = new User();
        HttpEntity<User> request = new HttpEntity<>(testUser);

        ResponseEntity<String> response = testRestTemplate.exchange("/account/joinUser", HttpMethod.POST, request, String.class);
        System.out.println("/account/joinUser");
        log.error(response.getStatusCode() + ":" + response.getBody());

        System.out.println("-".repeat(50));

        System.out.println("/account/info/1");
        response = testRestTemplate.exchange("/account/info/1", HttpMethod.GET, null, String.class);
        log.error(response.getStatusCode() + ":" + response.getBody());

        System.out.println("-".repeat(50));
        System.out.println("/account/update/1");
        response = testRestTemplate.exchange("/account/update/1", HttpMethod.PUT, request, String.class);
        log.error(response.getStatusCode() + ":" + response.getBody());

        System.out.println("-".repeat(50));
        System.out.println("/account/delete/1");
        response = testRestTemplate.exchange("/account/delete/1", HttpMethod.DELETE, null, String.class);
        log.error(response.getStatusCode() + ":" + response.getBody());
    }
}
