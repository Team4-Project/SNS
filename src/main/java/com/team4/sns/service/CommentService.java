package com.team4.sns.service;

import com.team4.sns.vo.Comment;

public interface CommentService {

    void writeComment(Comment comment);
    void deleteComment(Long commentId);
    void modifyComment(Long commentId, Comment comment);
    String getCommentContent(Long commentId);
}
