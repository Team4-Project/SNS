package com.team4.sns.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentLikeMapper {

    void likeCommentByUserIdAndCommentId(@Param("userId") Long userId,
                                         @Param("commentId") Long commentId);

    void unLikeCommentByUserIdAndCommentId(@Param("userId") Long userId,
                                           @Param("commentId") Long commentId);
}
