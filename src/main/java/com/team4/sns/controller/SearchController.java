package com.team4.sns.controller;

import com.team4.sns.service.PostService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchController {

	private PostService postService;

	@GetMapping("/search")
	public String searchByKeyword(Model model) {
		List<Post> postList = postService.getPostByKeyword();
		List<User> userList = postService.getUserByKeyword();

		model.addAttribute("users","userList");
		model.addAttribute("posts","postList");
		return "search";
	}
}
