package com.team4.sns.controller;

import com.team4.sns.service.UserService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.User;
import com.team4.sns.vo.UserSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    private UserService userService;
    private UserSessionService userSessionService;
    public UserController(UserService userService, UserSessionService userSessionService) {
        this.userService = userService;
        this.userSessionService = userSessionService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        Integer result = userService.createUser(user);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL Duplicated Account");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL creating user- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("create user success");
    }

    @PostMapping("/user/login")
    public ResponseEntity loginUser(@RequestBody User user, HttpServletResponse response) {
        // 로그인 시도 후, session 생성 시도 후 sessionId 가져옴
        Integer sessionId = userService.loginUser(user);
        // 로그인 실패 했다면 sessionId = -1 error
        if (sessionId == -1) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // sessionId 기반으로 쿠키 생성 후 response에 담아서 같이 보냄
        Cookie cookie = new Cookie("id", String.valueOf(sessionId));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/user")
    public ResponseEntity<String> editUser(@RequestBody User user, @CookieValue("id") Integer sessionId) {
        Integer result = userService.editUser(user, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired");
        }
        else if (result == -2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL editing user- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("edit user success");
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id, @CookieValue("id") Integer sessionId) {
        Integer result = userService.deleteUser(id, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired");
        }
        else if (result == -2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL deleting user- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("delete user success");
    }

    // ONLY FOR TESTING - 아래는 무시해주세요!
    @GetMapping("/user-test")
    public ResponseEntity<String> getUser(@RequestBody User user) {
        userService.getUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("getUser");
    }

}
