package com.team4.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserPageController {

    @RequestMapping("/user/signup")
    public String getSignupPage() {
        return "signup";
    }
    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }
}
