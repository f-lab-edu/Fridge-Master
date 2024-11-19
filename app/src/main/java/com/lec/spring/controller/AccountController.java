package com.lec.spring.controller;


import com.lec.spring.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {


    public static Long userId = 0L;

    @PostMapping("/joinUser")
    public ResponseEntity<?> joinUser(@RequestBody User user) {

        user.setId(++userId);

        user.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> accountInfo(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserinfo(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "회원탈퇴 성공.";
    }
}
