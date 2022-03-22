package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.CommentMapper;
import com.team4.sns.service.CommentService;
import com.team4.sns.vo.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public void writeComment(Comment comment) {
        commentMapper.writeCommentByPostId(comment);
    }
}
