package com.team4.sns.controller;

import com.team4.sns.service.FollowUserService;
import com.team4.sns.vo.FollowUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FollowUserController {

	private FollowUserService followUserService;

	@PostMapping("/{userId}/follow")
	public void followUser(@PathVariable Long userId, @RequestBody FollowUser followUser) {
		followUserService.followUser(followUser);
	}

	@PostMapping("/{userId}/unfollow")
	public void unfollowUser(@PathVariable Long userId, @RequestBody FollowUser followUser) {
		followUserService.unfollowUser(followUser);
	}
}
