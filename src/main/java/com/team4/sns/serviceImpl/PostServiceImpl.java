package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.PostMapper;
import com.team4.sns.service.ImageService;
import com.team4.sns.service.PostService;
import com.team4.sns.vo.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final ImageService imageService;

    @Override
    @Transactional
    public List<Post> getPostList(Integer userId, Integer page, Integer size) {
        return postMapper.getPostList(userId, size, (page-1)*size);
    }

    @Override
    @Transactional
    public Long writePost(Post post, List<MultipartFile> images) throws IOException {

        postMapper.writePost(post);
        Long registeredPostId = post.getId();

        if(images != null)
            imageService.uploadImage(registeredPostId, images);

        return post.getId();
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
    }

    @Override
    @Transactional
    public void modifyPost(Long postId, Post post, List<MultipartFile> images) throws IOException {

        if(images != null){
            imageService.deleteImage(postId);
            imageService.uploadImage(postId, images);
        }

        postMapper.modifyPost(postId, post);
    }

    @Override
    public List<Post> getPostByKeyword(String keyword) {
        return postMapper.getPostByKeyword(keyword);
    }

    @Override
    public List<Post> getMyPost(Integer userId, Integer page, Integer size) {
        return postMapper.getMyPost(userId, size, (page-1)*size);
    }

    @Override
    public List<Post> getMyLikePostList(Integer userId, Integer page, Integer size) {
        return postMapper.getMyLikePostList(userId, size, (page-1)*size);
    }

    @Override
    public List<Post> getMyCommentPostList(Integer userId, Integer page, Integer size) {
        return postMapper.getMyCommentPostList(userId, size, (page-1)*size);
    }

    @Override
    public void sharePost(Integer userId, Long postId){
        postMapper.sharePost(userId, postId);
    }

    @Override
    public Boolean isSharedPost(Integer userId, Long postId) {
        return postMapper.isSharedPost(userId, postId);
    }

    @Override
    public void deleteSharePost(Integer userId, Long postId) {
        postMapper.deleteSharePost(userId, postId);
    }
}
