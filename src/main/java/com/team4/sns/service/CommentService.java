package com.team4.sns.service;

import com.team4.sns.vo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentList(Long postId);
    void writeComment(Comment comment);
    void deleteComment(Long commentId);
    void modifyComment(Long commentId, Comment comment);
    String getCommentContent(Long commentId);
}
