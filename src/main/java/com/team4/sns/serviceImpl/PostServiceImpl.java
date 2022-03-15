package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.PostMapper;
import com.team4.sns.service.PostService;
import com.team4.sns.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    public void writePost(Post post) {
        postMapper.writePost(post);
    }

    @Override
    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
    }
}
