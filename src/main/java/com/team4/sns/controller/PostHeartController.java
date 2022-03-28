package com.team4.sns.controller;

import com.team4.sns.vo.PostHeartVO;
import com.team4.sns.service.PostHeartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PostHeartController {

    private PostHeartService postHeartService;

    @PostMapping("/{userId}/like")
    public void insertHeart(@PathVariable int userId, @RequestBody PostHeartVO postHeartVO) {
        postHeartService.insertHeart(postHeartVO);
    }

    @DeleteMapping("{userId}/like")
    public void deleteHeart(@PathVariable int userId, @RequestBody PostHeartVO postHeartVO) {
        postHeartService.deleteHeart(postHeartVO);
    }
}
