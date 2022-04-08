package com.team4.sns.service;

import com.team4.sns.vo.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getPostList(Integer userId, Integer page, Integer size);
    Long writePost(Post post, List<MultipartFile> images) throws IOException;
    void deletePost(Long postId);
    void modifyPost(Long postId, Post post, List<MultipartFile> images) throws IOException;
    List<Post> getPostByKeyword(String keyword);
    List<Post> getMyPost(Integer userId, Integer page, Integer size);
    List<Post> getMyLikePostList(Integer userId, Integer page, Integer size);
    List<Post> getMyCommentPostList(Integer userId, Integer page, Integer size);
    List<Post> getMySharePostList(Integer userId, Integer page, Integer size);
    void sharePost(Integer userId, Long postId);
    Boolean isSharedPost(Integer userId, Long postId);
    void deleteSharePost(Integer userId, Long postId);
}
