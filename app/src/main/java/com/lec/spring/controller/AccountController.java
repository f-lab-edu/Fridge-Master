package com.lec.spring.controller;


import com.lec.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/account")
public class AccountController {


    public final Map<Long, User> userStore = new ConcurrentHashMap<Long, User>();
//    ConcurrentHashmap은 읽기 작업에는 여러 쓰레드가 동시에 읽을 수 있지만, 쓰기 작업에는 특정 세그먼트 or 버킷에 대한 Lock을 사용한다.


    private AtomicLong id = new AtomicLong(0);

    @PostMapping("/joinUser")
    public ResponseEntity<?> joinUser(@RequestBody(required = false) User user) {
//       todo
//       RequestBody 에 무엇을 넣을지
//        api 공통 아웃풋 구조
        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("입력된 정보가 없습니다.");
        }

        boolean usernameExists = userStore.values().stream()
                .anyMatch(existingUser -> existingUser.getUsername().equals(user.getUsername()));
        if(usernameExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username '" + user.getUsername() + "'은 이미 사용 중입니다.");
        }

        user.setId(id.incrementAndGet());
        userStore.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Long id) {


        if (!userStore.containsKey(id)) {
            return ResponseEntity.badRequest().body("유효하지 않은 ID");
        }

        return ResponseEntity.ok(userStore.get(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {

        if (!userStore.containsKey(id)) {
            return ResponseEntity.badRequest().body("유효하지 않은 ID");
        }

        User oldUser = userStore.get(id);


        oldUser.setNickname(user.getNickname());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());

        userStore.put(id, oldUser);
        return ResponseEntity.ok(userStore.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        if (!userStore.containsKey(id)) {
            return ResponseEntity.badRequest().body("유효하지 않은 ID");
        }

        userStore.remove(id);
        return ResponseEntity.ok("삭제 성공");
    }


    public boolean idExists(Long id) {
        return userStore.containsKey(id);
    }
}
