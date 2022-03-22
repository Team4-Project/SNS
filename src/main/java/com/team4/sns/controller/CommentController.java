package com.team4.sns.controller;

import com.team4.sns.controller.dto.CommentRequestDto;
import com.team4.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/comment")
    public void writeComment(@RequestBody CommentRequestDto commentRequestDto){
        //세션 미 구현으로 인한 userId=1
        commentRequestDto.setUserId(1L);
        commentService.writeComment(commentRequestDto.toComment());
    }

}
