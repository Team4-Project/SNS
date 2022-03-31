package com.team4.sns.mapper;

import com.team4.sns.vo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentListByPostId(@Param("postId") Long postId);
    void writeCommentByPostId(@Param("comment") Comment comment);
    void deleteCommentByCommentId(@Param("commentId") Long commentId);
    void modifyCommentByCommentId(@Param("commentId") Long commentId, @Param("comment") Comment comment);
    String getCommentContentByCommentId(@Param("commentId") Long commentId);
}
