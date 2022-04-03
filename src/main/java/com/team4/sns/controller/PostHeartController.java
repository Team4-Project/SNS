package com.team4.sns.controller;

import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.PostHeartVO;
import com.team4.sns.service.PostHeartService;
import com.team4.sns.vo.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PostHeartController {

    private PostHeartService postHeartService;
    private UserSessionService userSessionService;

    @PostMapping("/like")
    public void insertHeart(@CookieValue("id") Integer sessionId, @RequestBody PostHeartVO postHeartVO) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);

        postHeartVO.setUserId(userSession.getUserId());

        if(postHeartService.isValidateHeart(postHeartVO))
            postHeartService.deleteHeart(postHeartVO);
        else
            postHeartService.insertHeart(postHeartVO);
    }

    @DeleteMapping("/like")
    public void deleteHeart(@CookieValue("id") Integer sessionId, @RequestBody PostHeartVO postHeartVO) {
        postHeartService.deleteHeart(postHeartVO);
    }
}
