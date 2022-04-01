package com.team4.sns.mapper;

import com.team4.sns.vo.FollowUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowUserMapper {

	public void followUser(FollowUser followUser);

	public void unfollowUser(FollowUser followUser);
}
