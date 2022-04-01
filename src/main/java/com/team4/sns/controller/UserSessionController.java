package com.team4.sns.controller;

import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.UserSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSessionController {
    private UserSessionService userSessionService;
    public UserSessionController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @GetMapping("/user/session")
    public UserSession getSession(@RequestParam Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        return userSession;
    }

    @DeleteMapping("/user/session")
    public ResponseEntity deleteSession(@RequestParam Integer id) {
        System.out.println(id);
        userSessionService.deleteUserSession(id);
        return ResponseEntity.ok().build();
    }
}
