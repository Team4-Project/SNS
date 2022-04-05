package com.team4.sns.service;

import com.team4.sns.vo.FollowUser;
import com.team4.sns.vo.PostHeartVO;

public interface FollowUserService {

	void followUser(FollowUser followUser);

	void unfollowUser(FollowUser followUser);

	Boolean isValidateUserFollow(FollowUser followUser);
}
