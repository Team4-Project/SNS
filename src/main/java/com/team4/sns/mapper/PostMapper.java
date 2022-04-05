package com.team4.sns.mapper;

import com.team4.sns.vo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> getPostList(@Param("userId") Integer userId, @Param("limit") Integer limit, @Param("offset") Integer offset);
    void writePost(@Param("post") Post post);
    void deletePost(@Param("postId") Long postId);
    void modifyPost(@Param("postId") Long postId,
                    @Param("post") Post post);
    List<Post> getPostByKeyword(@Param("keyword") String keyword);
    List<Post> getMyPost(@Param("userId") Integer userId, @Param("limit") Integer limit, @Param("offset") Integer offset);
    List<Post> getMyLikePostList(@Param("userId") Integer userId, @Param("limit") Integer limit, @Param("offset") Integer offset);
    List<Post> getMyCommentPostList(@Param("userId") Integer userId, @Param("limit") Integer limit, @Param("offset") Integer offset);
}
