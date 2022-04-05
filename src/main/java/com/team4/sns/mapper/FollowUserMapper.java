package com.team4.sns.mapper;

import com.team4.sns.vo.FollowUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowUserMapper {

	void followUser(FollowUser followUser);

	void unfollowUser(FollowUser followUser);

	Integer isValidateUserFollow(FollowUser followUser);
}
