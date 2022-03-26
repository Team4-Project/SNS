package com.team4.sns.controller;

import com.team4.sns.service.ReplyLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class ReplyLikeController {
    private ReplyLikeService replyLikeService;
    public ReplyLikeController(ReplyLikeService replyLikeService) {
        this.replyLikeService = replyLikeService;
    }

    @PostMapping("/reply/like/{replyId}")
    public ResponseEntity<String> likeReply(@PathVariable(name="replyId") Integer id, @CookieValue("id") Integer sessionId) {
        Integer result = replyLikeService.likeReply(id, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL like reply- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("like reply success");
    }

    @PutMapping("/reply/unlike/{replyId}")
    public ResponseEntity<String> unlikeReply(@PathVariable Integer id, @CookieValue("id") Integer sessionId) {
        Integer result = replyLikeService.unlikeReply(id, sessionId);

        if (result == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }
        else if (result == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL unlike reply- SQL");
        }
        return ResponseEntity.status(HttpStatus.OK).body("unlike reply success");
    }

}
