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
    public void writePost(Post post, List<MultipartFile> images) throws IOException {

        postMapper.writePost(post);
        Long registeredPostId = post.getId();

        if(images != null)
            imageService.uploadObject(registeredPostId, images);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
    }

    @Override
    public void modifyPost(Long postId, Post post) {
        postMapper.modifyPost(postId, post);
    }
}
