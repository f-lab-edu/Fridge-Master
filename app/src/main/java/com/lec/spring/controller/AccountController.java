package com.lec.spring.controller;


import com.lec.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/account")
public class AccountController {

    public static final Map<Long, User> userStore = new ConcurrentHashMap<Long, User>();
//    ConcurrentHashMap은 읽기 작업에는 여러 쓰레드가 동시에 읽을 수 있지만, 쓰기 작업에는 특정 세그먼트 or 버킷에 대한 Lock을 사용한다.


    private static AtomicLong id = new AtomicLong(0);

    @PostMapping("/joinUser")
    public ResponseEntity<?> joinUser(@RequestBody User user) {

        user.setId(id.incrementAndGet());

        userStore.put(user.getId(), user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Long id) {


        if (!userStore.containsKey(id)) {
            return ResponseEntity.ok("유효하지 않은 ID 값입니다.");
        }

        return ResponseEntity.ok(userStore.get(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {

        if (!userStore.containsKey(id)) {
            return ResponseEntity.ok("유효하지 않은 ID 값입니다.");
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
            return ResponseEntity.ok("유효하지 않은 ID 값입니다.");
        }

        userStore.remove(id);
        return ResponseEntity.ok("삭제 성공");
    }


    public boolean idExists(Long id) {
        return userStore.containsKey(id);
    }
}
