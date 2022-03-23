package com.team4.sns.controller;

import com.team4.sns.service.ReplyService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.Reply;
import com.team4.sns.vo.UserSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {
    private ReplyService replyService;
    private UserSessionService userSessionService;

    public ReplyController(ReplyService replyService, UserSessionService userSessionService) {
        this.replyService = replyService;
        this.userSessionService = userSessionService;
    }

    // reply(Integer commentId, String content)
    @PostMapping("/reply")
    public ResponseEntity<String> createReply(@RequestBody Reply reply, @CookieValue("id") Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        // GET logInUserId
        Integer logInUserId = userSession.getUserId();

        // SET userID -> reply 객체
        reply.setUserId(logInUserId);
        boolean createReplyResult = replyService.createReply(reply);
        if (createReplyResult != true) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("create reply FAIL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("create reply success");
    }

    // reply(Integer id, Integer commentId, String content)
    @PutMapping("/reply")
    public ResponseEntity<String> editReply(@RequestBody Reply reply, @CookieValue("id") Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        // "현재 로그인한 유저"와 "수정하려는 대댓글의 userId"가 다르면 error
        Integer logInUserId = userSession.getUserId();
        boolean editReplyResult = replyService.editReply(reply, logInUserId);
        if (editReplyResult == false) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user editing reply");
        }
        return ResponseEntity.status(HttpStatus.OK).body("edit reply success");
    }

    @DeleteMapping("/reply")
    public ResponseEntity<String> deleteReply(@RequestParam Integer id, @CookieValue("id") Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        // "현재 로그인한 유저"와 "지우려는 유저정보"가 다른 userId를 가지고 있다면 error
        Integer logInUserId = userSession.getUserId();
        boolean deleteReplyResult = replyService.deleteReply(id, logInUserId);
        if (deleteReplyResult != true) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user deleting reply");
        }
        return ResponseEntity.status(HttpStatus.OK).body("delete reply success");
    }
}
