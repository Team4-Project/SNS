package com.team4.sns.controller;

import com.team4.sns.service.UserService;
import com.team4.sns.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserPageController {
    private UserService userService;
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/signup")
    public String getSignupPage() {
        return "signup";
    }

    @RequestMapping("/user/login")
    public String getLogInPage() { return "login"; }

    @RequestMapping("/user/profile")
    public String getUserProfileChangePage(Model model,
                                           @CookieValue(value = "id", required = false) Integer sessionId) {
        User user = userService.getUserBySessionId(sessionId);
        model.addAttribute("account", user.getAccount());
        model.addAttribute("name", user.getName());
        return "userProfileChange";
    }

}
