package com.team4.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrendingPageController {

    @RequestMapping("/trending")
    public String getTrendingPage() {
        return "trending";
    }

}
