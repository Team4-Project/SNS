package com.team4.sns.service;


import com.team4.sns.mapper.TrendingMapper;
import com.team4.sns.mapper.UserMapper;
import com.team4.sns.util.S3Util;
import com.team4.sns.vo.Post;
import com.team4.sns.vo.Tag;
import com.team4.sns.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrendingService {
    private UserMapper userMapper;
    private UserSessionService userSessionService;
    private TrendingMapper trendingMapper;
    private final S3Util s3Util;

    public TrendingService(UserMapper userMapper, UserSessionService userSessionService, S3Util s3Util, TrendingMapper trendingMapper) {
        this.userMapper = userMapper;
        this.userSessionService = userSessionService;
        this.s3Util = s3Util;
        this.trendingMapper = trendingMapper;
    }
    public List<User> getTrendingUserList() {
        return trendingMapper.getTrendingUserList();
    }
    public List<Post> getTrendingPostList() {
        return trendingMapper.getTrendingPostList();
    }
    public List<Tag> getTrendingTagList() {return trendingMapper.getTrendingTagList();}

}
