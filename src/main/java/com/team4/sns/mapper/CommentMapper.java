package com.team4.sns.mapper;

import com.team4.sns.vo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    void writeCommentByPostId(@Param("comment") Comment comment);
    void deleteCommentByCommentId(@Param("commentId") Long commentId);
    void modifyCommentByCommentId(@Param("commentId") Long commentId, @Param("comment") Comment comment);
}
