package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.CommentMapper;
import com.team4.sns.service.CommentService;
import com.team4.sns.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentList(Long postId) {
        return commentMapper.getCommentListByPostId(postId);
    }

    @Override
    public void writeComment(Comment comment) {
        commentMapper.writeCommentByPostId(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteCommentByCommentId(commentId);
    }

    @Override
    public void modifyComment(Long commentId, Comment comment) {
        commentMapper.modifyCommentByCommentId(commentId, comment);
    }

    @Override
    public String getCommentContent(Long commentId) {
        return commentMapper.getCommentContentByCommentId(commentId);
    }
}
