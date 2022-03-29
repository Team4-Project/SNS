package com.team4.sns.controller;

import com.team4.sns.service.PostService;
import com.team4.sns.service.UserService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;
    private final PostService postService;

    @RequestMapping("/user/signup")
    public String getSignupPage() {
        return "signup";
    }

    @RequestMapping("/")
    public String getIndexPage(Model model) {
        List<User> recommendedUsers = userService.getSevenRecommendationAboutUser();
        List<Post> postList = postService.getPostList();

        model.addAttribute("recommendedUsers", recommendedUsers);
        model.addAttribute("postList", postList);
        return "index";
    }

    @RequestMapping("/index.html")
    public String getIndexPageByHtmlUrl(){
        return "index";
    }

    @RequestMapping("/profile.html")
    public String getProfilePage(){
        return "profile";
    }
}
