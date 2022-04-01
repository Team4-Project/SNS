package com.team4.sns.controller;

import com.team4.sns.service.ReplyService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {
    private ReplyService replyService;
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    // reply(Integer commentId, String content)
    @PostMapping("/reply")
    public ResponseEntity<String> createReply(@RequestBody Reply reply, @CookieValue("id") Integer sessionId) {
        Integer result = replyService.createReply(reply, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        else if (result == -2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL creating reply- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("create reply success");
    }

    // reply(Integer id, Integer commentId, String content)
    @PutMapping("/reply")
    public ResponseEntity<String> editReply(@RequestBody Reply reply, @CookieValue("id") Integer sessionId) {
        Integer result = replyService.editReply(reply, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        else if (result == -2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL editing reply- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("edit reply success");
    }

    @DeleteMapping("/reply")
    public ResponseEntity<String> deleteReply(@RequestParam Integer id, @CookieValue("id") Integer sessionId) {
        Integer result = replyService.deleteReply(id, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        else if (result == -2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL deleting reply -SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("delete reply success");
    }
}
