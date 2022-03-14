package com.team4.sns.controller;

import com.team4.sns.controller.dto.PostRequestDto;
import com.team4.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/post")
    public void writePost(@RequestBody @Validated PostRequestDto postRequestDto){
        //세션 구현전 이기에, userId =1로 고정
        postRequestDto.setUserId(1L);
        postService.writePost(postRequestDto.toPost());
    }

}
