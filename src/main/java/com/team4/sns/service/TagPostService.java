package com.team4.sns.service;

import com.team4.sns.mapper.TagPostMapper;
import org.springframework.stereotype.Service;

@Service
public class TagPostService {
    private TagPostMapper tagPostMapper;

    public TagPostService(TagPostMapper tagPostMapper) {
        this.tagPostMapper = tagPostMapper;
    }
    public void createTagPost(Integer postId, Integer tagId) {
        tagPostMapper.createTagPost(postId, tagId);
    }
    public void deleteTagPost(Integer postId, Integer tagId) {
        tagPostMapper.deleteTagPost(postId, tagId);
    }
}
