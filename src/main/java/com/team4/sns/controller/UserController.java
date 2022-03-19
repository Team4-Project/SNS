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
        boolean userResult = userService.getUserByAccount(user);
        if (userResult == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicated Account");
        }
        // 중복 이메일이 없다면 생성 가능
        else {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("account signup success");
        }
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

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody User user, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        // 존재하지 않는 session 이라면 error
        // ex) 세션 시간 만료로 세션 삭제 등등
        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        // "현재 로그인한 유저"와 "수정하려는 유저정보"가 다른 userId를 가지고 있다면 error
        Integer userId = userSession.getUserId();
        if (userId.equals(user.getId())==false) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        userService.updateUser(user, userId);
        return ResponseEntity.status(HttpStatus.OK).body("updateUser success");
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        // 존재하지 않는 session 이라면 error
        // 즉, 세션 시간 만료로 세션 삭제 등등
        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        // "현재 로그인한 유저"와 "지우려는 유저정보"가 다른 userId를 가지고 있다면 error
        Integer userId = userSession.getUserId();
        if (id.equals(userId) == false) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("delete user success");
    }

}
