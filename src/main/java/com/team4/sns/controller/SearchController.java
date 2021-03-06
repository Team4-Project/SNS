package com.team4.sns.controller;

import com.team4.sns.service.PostService;
import com.team4.sns.service.TagService;
import com.team4.sns.service.UserService;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.Tag;
import com.team4.sns.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class SearchController {

	private PostService postService;
	private UserService userService;
	private TagService tagService;

	@GetMapping("/search")
		public String searchByKeyword(Model model, @RequestParam String keyword) {
		List<Post> postList = postService.getPostByKeyword(keyword);
		List<User> userList = userService.getUserByKeyword(keyword);
		List<Tag> tagList = tagService.getTagByKeyword(keyword);

		model.addAttribute("posts",postList);
		model.addAttribute("users", userList);
		model.addAttribute("tags", tagList);
		return "search";
	}
}
