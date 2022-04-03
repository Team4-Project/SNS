package com.team4.sns.controller;

import com.team4.sns.controller.dto.PostRequestDto;
import com.team4.sns.service.PostService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserSessionService userSessionService;

    @GetMapping(value = "/post")
    public ResponseEntity<List<Post>> getPostList(@RequestParam(name = "page") Integer page){
        return new ResponseEntity<>(postService.getPostList(page, 13), HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Long> writePost(@Validated PostRequestDto postRequestDto,
                          @RequestPart(name = "images", required = false) List<MultipartFile> images,
                          @CookieValue("id")Integer sessionId) throws IOException {

        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            throw new ExportException("wrong");
        }

        Long logInUserId = Integer.toUnsignedLong(userSession.getUserId());
        postRequestDto.setUserId(logInUserId);

        return new ResponseEntity<Long>(postService.writePost(postRequestDto.toPost(), images), HttpStatus.CREATED);
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
