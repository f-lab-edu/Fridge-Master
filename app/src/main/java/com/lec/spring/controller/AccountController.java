package com.lec.spring.controller;


import com.lec.spring.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Map<Long, User> userStore = new HashMap<Long, User>();
    private static Long id = 0L;

    @PostMapping("/joinUser")
    public ResponseEntity<?> joinUser(@RequestBody User user) {

        user.setId(++id);

        user.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();

        userStore.put(user.getId(), user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Long id) {

        return ResponseEntity.ok(userStore.get(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {

        User oldUser = userStore.get(id);


        oldUser.setNickname(user.getNickname());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());

        userStore.put(id, oldUser);
        return ResponseEntity.ok(userStore.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userStore.remove(id);
        return ResponseEntity.ok("삭제 성공");
    }
}
