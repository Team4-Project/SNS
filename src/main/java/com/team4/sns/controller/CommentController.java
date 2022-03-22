package com.team4.sns.controller;

import com.team4.sns.controller.dto.CommentRequestDto;
import com.team4.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/comment")
    public void writeComment(@RequestBody @Validated CommentRequestDto commentRequestDto){
        //세션 미 구현으로 인한 userId=1
        commentRequestDto.setUserId(1L);
        commentService.writeComment(commentRequestDto.toComment());
    }

    @DeleteMapping(value ="/comment/{comment-id}")
    public void deleteComment(@PathVariable(value = "comment-id") Long commentId){
        commentService.deleteComment(commentId);
    }

    @PatchMapping(value ="/commet/{comment-id}")
    public void modifyComment(@PathVariable(value = "comment-id") Long commentId,
                              @RequestBody @Validated CommentRequestDto commentRequestDto){
        commentService.modifyComment(commentId, commentRequestDto.toComment());
    }

}
