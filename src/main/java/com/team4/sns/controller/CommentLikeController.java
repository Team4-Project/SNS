package com.team4.sns.controller;

import com.team4.sns.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping(value = "/comment/like/{comment-id}")
    public void likeComment(@PathVariable(name = "comment-id") Long commentId){

        // 세션 미적용으로 인한 userId=1 고정
        Long userId = 1L;
        commentLikeService.likeComment(userId, commentId);
    }

    @DeleteMapping(value = "/comment/un-like/{comment-id}")
    public void unLikeComment(@PathVariable(name = "comment-id") Long commentId){
        // 세션 미적용으로 인한 userId=1 고정
        Long userId = 1L;
        commentLikeService.unLikeComment(userId, commentId);
    }
}
