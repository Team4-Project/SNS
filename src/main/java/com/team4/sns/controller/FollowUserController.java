package com.team4.sns.controller;

import com.team4.sns.service.FollowUserService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.FollowUser;
import com.team4.sns.vo.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FollowUserController {

	private FollowUserService followUserService;
	private UserSessionService userSessionService;

	@PostMapping("/follow")
	public void followUser(@CookieValue("id") Integer sessionId, @RequestBody FollowUser followUser) {
		UserSession userSession = userSessionService.getUserSessionById(sessionId);
		followUser.setUserId(Integer.toUnsignedLong(userSession.getUserId()));

		if(followUserService.isValidateUserFollow(followUser)){
			followUserService.followUser(followUser);
		}
		else{
			followUserService.unfollowUser(followUser);
		}

	}

	@PostMapping("/{userId}/unfollow")
	public void unfollowUser(@PathVariable Long userId, @RequestBody FollowUser followUser) {
		followUserService.unfollowUser(followUser);
	}
}
