package com.lec.spring.controller;


import com.lec.spring.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {



    @PostMapping("/joinUser")
    public ResponseEntity<?> joinUser(@RequestBody User user) {

        
        return ResponseEntity.ok("회원가입 성공.");
    }

    @GetMapping("/account/info/{id}")
    public User accountInfo(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/account/update/{id}")
    public String updateUserinfo(@PathVariable Long id, @RequestBody User user) {
        return "회원정보 수정 완료.";
    }

    @DeleteMapping("/account/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "회원탈퇴 성공.";
    }
}
