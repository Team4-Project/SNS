package com.team4.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserPageController {
    @RequestMapping("/user/signup")
    public String getSignupPage() {
        return "signup";
    }

    @RequestMapping("/user/login")
    public String getLogInPage() { return "login"; }

    @RequestMapping("/user/profile")
    public String getUserProfileChangePage() { return "userProfileChange"; }

}
