package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.CommentLikeMapper;
import com.team4.sns.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeMapper commentLikeMapper;

    @Override
    public void likeComment(Long userId, Long commentId) {
        commentLikeMapper.likeCommentByUserIdAndCommentId(userId, commentId);
    }

    @Override
    public void unLikeComment(Long userId, Long commentId) {
        commentLikeMapper.unLikeCommentByUserIdAndCommentId(userId, commentId);
    }
}
