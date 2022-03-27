package com.team4.sns.controller;

import com.team4.sns.service.UserService;
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

    @RequestMapping("/user/signup")
    public String getSignupPage() {
        return "signup";
    }

    @RequestMapping("/")
    public String getIndexPage(Model model) {
        List<User> recommendedUsers = userService.getSevenRecommendationAboutUser();

        model.addAttribute("recommendedUsers", recommendedUsers);
        return "index";
    }
}
