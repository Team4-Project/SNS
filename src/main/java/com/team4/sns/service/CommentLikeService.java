package com.team4.sns.service;

public interface CommentLikeService {

    void likeComment(Long userId, Long commentId);
    void unLikeComment(Long userId, Long commentId);
}
