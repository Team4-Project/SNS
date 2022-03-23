package com.team4.sns.controller;

import com.team4.sns.service.TagService;
import com.team4.sns.service.UserSessionService;
import com.team4.sns.vo.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TagController {
    private TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tag")
    public List<Tag> getTagListByPostId(@RequestParam Integer postId) {
        List<Tag> tagList = tagService.getTagListByPostId(postId);
        return tagList;
    }

    // "TrendingController" 에서 getTagListTrending(page, size) 호출
    // 구현중...
    @GetMapping("/tag/trending")
    public List<Tag> getTagListTrending(Integer page, Integer size) {
        List<Tag> listTag = new ArrayList<>();
        return listTag;
    }

    // "SearchController" 에서 getTagListSearch(content, page, size) 호출
    @GetMapping("/tag/search")
    public List<Tag> getTagListSearch(String content, Integer page, Integer size) {
        List<Tag> listTag = tagService.getTagListSearch(content, page, size);
        return listTag;
    }

    // 이미 인증받은 유저가 call 했다고 가정
    // Integer postId 와 String content(모든 tag의 contents를 포함한)를 인자로 받는다고 가정
    @PostMapping("/tag")
    public ResponseEntity<String> createTag(@RequestParam Integer postId, @RequestParam String contentList) {
        tagService.createTag(postId, contentList);
        return ResponseEntity.status(HttpStatus.OK).body("create tag success");
    }
    // 이미 인증받은 유저가 call 했다고 가정
    // id, postId 가 들어있는 tag를 인자로 받는다고 가정
    @PatchMapping("/tag")
    public ResponseEntity<String> editTag(@RequestBody Tag tag) {
        // 수정하려는 tag content로 빈칸이 들어왔을 경우 error
        if (tag.getContent().length() < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("edited tag cannot be empty");
        }
        tagService.editTag(tag);
        return ResponseEntity.status(HttpStatus.OK).body("edit tag success");
    }

    @DeleteMapping("/tag")
    public ResponseEntity<String> deleteTag(@RequestBody Tag tag) {
        tagService.deleteTag(tag);
        return ResponseEntity.status(HttpStatus.OK).body("delete tag success");
    }


}
