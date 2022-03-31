package com.team4.sns.service;

import com.team4.sns.vo.FollowUser;

public interface FollowUserService {

	void followUser(FollowUser followUser);

	void unfollowUser(FollowUser followUser);
}
