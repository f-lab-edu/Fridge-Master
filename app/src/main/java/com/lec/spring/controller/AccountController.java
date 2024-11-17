package com.lec.spring.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @PostMapping("/joinUser")
    public String joinUser() {

        return "회원가입 성공.";
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
