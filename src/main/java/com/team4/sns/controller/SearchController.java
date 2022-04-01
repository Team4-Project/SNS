package com.team4.sns.controller;

import com.team4.sns.service.PostService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class SearchController {

	private PostService postService;

	@GetMapping("/search/{keyword}")
	public String searchByKeyword(Model model, @PathVariable String keyword) {
		List<Post> postList = postService.getPostByKeyword(keyword);

		model.addAttribute("posts",postList);
		return "search";
	}
}
