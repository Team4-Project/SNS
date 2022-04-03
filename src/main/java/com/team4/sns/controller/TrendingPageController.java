package com.team4.sns.controller;

import com.team4.sns.service.TrendingService;
import com.team4.sns.service.UserService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.Tag;
import com.team4.sns.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class TrendingPageController {
    private UserService userService;
    private TrendingService trendingService;

    @RequestMapping("/trending")
    public String getTrendingPage(Model model, @CookieValue(value = "id", required = false) Integer sessionId) {
        List<User> popularUsers = trendingService.getTrendingUserList();
        List<Post> popularPosts = trendingService.getTrendingPostList();
        List<Tag> popularTags = trendingService.getTrendingTagList();
        for (Post post : popularPosts) {
            System.out.println(post);
        }
        model.addAttribute("popularUsers", popularUsers);
        model.addAttribute("popularPosts", popularPosts);
        model.addAttribute("popularTags", popularTags);
        return "trending";
    }

}
