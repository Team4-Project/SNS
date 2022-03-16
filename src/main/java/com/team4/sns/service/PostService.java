package com.team4.sns.service;

import com.team4.sns.vo.Post;

public interface PostService {
    void writePost(Post post);
    void deletePost(Long postId);
    void modifyPost(Long postId, Post post);
}
