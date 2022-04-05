package com.team4.sns.controller;

import com.team4.sns.service.PostService;
import com.team4.sns.service.UserService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.User;
import com.team4.sns.vo.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexPageController {

    private final UserService userService;
    private final PostService postService;
    private final UserSessionService userSessionService;

    @RequestMapping(value = "/")
    public String getIndexPage(@CookieValue(name = "id", required = false) Integer sessionId,
                               Model model) {

        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        Integer userId = null;

        if(userSession != null)
            userId = userSession.getUserId();

        List<User> recommendedUsers = userService.getSevenRecommendationAboutUser(userId);
        List<Post> postList = postService.getPostList(userId, 1, 13);
        List<User> popularUsers = userService.getNinePopularUser(userId);

        model.addAttribute("recommendedUsers", recommendedUsers);
        model.addAttribute("postList", postList);
        model.addAttribute("popularUsers", popularUsers);
        return "index";
    }

    @RequestMapping(value = "/sign-in-up")
    public String getSignInAndSignUpPage(){
        return "sign-in-up";
    }

    @RequestMapping("/profile/{user-id}")
    public String getProfilePage(@PathVariable(name = "user-id") Integer userId,
                                 Model model){
        User user = userService.getUserInfo(userId);
        List<Post> myPostList = postService.getMyPost(userId, 1 ,13);
        List<Post> myLikePostList = postService.getMyLikePostList(userId, 1, 13);
        List<Post> myCommentPostList = postService.getMyCommentPostList(userId, 1, 13);
        model.addAttribute("user", user);
        model.addAttribute("myPostList", myPostList);
        model.addAttribute("myLikePostList", myLikePostList);
        model.addAttribute("myCommentPostList", myCommentPostList);
        return "profile";
    }
}
