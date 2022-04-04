package com.team4.sns.serviceImpl;


import com.team4.sns.mapper.FollowUserMapper;
import com.team4.sns.service.FollowUserService;
import com.team4.sns.vo.FollowUser;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FollowUserServiceImpl implements FollowUserService {

	private FollowUserMapper followUserMapper;

	@Override
	public void followUser(FollowUser followUser) {
		followUserMapper.followUser(followUser);
	}

	@Override
	public void unfollowUser(FollowUser followUser) {
		followUserMapper.unfollowUser(followUser);
	}

	@Override
	public Boolean isValidateUserFollow(FollowUser followUser) {
		if(followUserMapper.isValidateUserFollow(followUser) == null)
			return true;

		return false;
	}
}
