package com.team4.sns.service;

import com.team4.sns.mapper.TagMapper;
import com.team4.sns.vo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private TagMapper tagMapper;
    private TagPostService tagPostService;
    public TagService(TagMapper tagMapper, TagPostService tagPostService) {
        this.tagMapper = tagMapper;
        this.tagPostService = tagPostService;
    }

    // postId에 해당하는 tag를 모두 불러옴
    public List<Tag> getTagListByPostId(Integer postId) {
        return tagMapper.getTagListByPostId(postId);
    }

    // postId에 해당하는 tag를 생성하고,
    // postId와 만들어진 tagId 기반으로 post_tag 생성
    public void createTag(Integer postId, String contentList) {
        // contentList 에는 input 으로 들어온 모든 tag의 contents가 '#'을 기준으로 합쳐져있음
        String[] content = contentList.split("#");
        for (int i = 1; i < content.length; i++) {
            Tag tag = new Tag(postId, content[i]);
            tagMapper.createTag(tag);
            tagPostService.createTagPost(postId, tag.getId());
        }
    }
    public void editTag(Tag tag) {
        tagMapper.editTag(tag);
    }

    public void deleteTag(Tag tag) {
        tagMapper.deleteTag(tag);
        tagPostService.deleteTagPost(tag.getPostId(), tag.getId());
    }

    public Tag getTag(Integer tagId) {
        return tagMapper.getTag(tagId);
    }

    public List<Tag> getTagListSearch(String content, Integer page, Integer size) {
        return tagMapper.getTagListSearch(content, size, (page-1) * size);
    }

    public List<Tag> getTagByKeyword(String keyword) {
        return tagMapper.getTagByKeyword(keyword);
    }

}
