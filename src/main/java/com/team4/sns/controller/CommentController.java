package com.team4.sns.controller;

import com.team4.sns.controller.dto.CommentRequestDto;
import com.team4.sns.service.CommentService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.Comment;
import com.team4.sns.vo.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserSessionService userSessionService;


    @GetMapping(value = "/comment/{post-id}")
    public ResponseEntity<List<Comment>> getCommentList(@PathVariable("post-id") Long postId){
        return new ResponseEntity<>(commentService.getCommentList(postId), HttpStatus.OK);
    }

    @PostMapping(value = "/comment")
    public void writeComment(@CookieValue(value = "id", required = false) Integer userId,
                             @RequestBody @Validated CommentRequestDto commentRequestDto){
        UserSession userSession = userSessionService.getUserSessionById(userId);
        commentRequestDto.setUserId(Integer.toUnsignedLong(userSession.getUserId()));
        commentService.writeComment(commentRequestDto.toComment());
    }

    @DeleteMapping(value ="/comment/{comment-id}")
    public void deleteComment(@PathVariable(value = "comment-id") Long commentId){
        commentService.deleteComment(commentId);
    }

    @PatchMapping(value ="/comment/{comment-id}")
    public void modifyComment(@PathVariable(value = "comment-id") Long commentId,
                              @RequestBody @Validated CommentRequestDto commentRequestDto){
        commentService.modifyComment(commentId, commentRequestDto.toComment());
    }

    @GetMapping(value ="/comment/content/{comment-id}")
    public ResponseEntity<String> getCommentContentWhenModifyComment(@PathVariable(value = "comment-id") Long commentId){
        return new ResponseEntity<String>(commentService.getCommentContent(commentId), HttpStatus.OK);
    }

}
