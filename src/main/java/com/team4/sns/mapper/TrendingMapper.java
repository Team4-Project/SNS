package com.team4.sns.mapper;

import com.team4.sns.vo.Post;
import com.team4.sns.vo.Tag;
import com.team4.sns.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrendingMapper {
    List<User> getTrendingUserList();
    List<Post> getTrendingPostList();
    List<Tag> getTrendingTagList();
}
