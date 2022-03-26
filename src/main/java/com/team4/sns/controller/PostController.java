package com.team4.sns.controller;

import com.team4.sns.controller.dto.PostRequestDto;
import com.team4.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/post")
    public void writePost(@Validated PostRequestDto postRequestDto,
                          @RequestPart(name = "images", required = false) List<MultipartFile> images) throws IOException {

        //세션 구현전 이기에, userId =1로 고정
        postRequestDto.setUserId(1L);

        postService.writePost(postRequestDto.toPost(), images);
    }

    @DeleteMapping(value ="/post/{post-id}")
    public void deletePost(@PathVariable(name = "post-id") Long postId){
        postService.deletePost(postId);
    }

    @PatchMapping(value ="/post/{post-id}")
    public void modifyPost(@PathVariable(name ="post-id") Long postId,
                           @RequestPart(name = "images", required = false) List<MultipartFile> images,
                           @Validated PostRequestDto postRequestDto) throws IOException {
        postService.modifyPost(postId, postRequestDto.toPost(), images);
    }
}
