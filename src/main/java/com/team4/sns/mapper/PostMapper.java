package com.team4.sns.mapper;

import com.team4.sns.vo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> getPostList();
    void writePost(@Param("post") Post post);
    void deletePost(@Param("postId") Long postId);
    void modifyPost(@Param("postId") Long postId,
                    @Param("post") Post post);
}
