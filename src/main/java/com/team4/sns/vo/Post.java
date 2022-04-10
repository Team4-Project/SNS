package com.team4.sns.vo;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class Post {

    private Long id;
    private Long userId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private User user;
    private List<String> postImageList;
    private Integer likeNum;
    private Integer commentNum;
    private Integer shareNum;
    private List<String> tagList;

    @Builder
    public Post(Long userId, String content){
        this.userId = userId;
        this.content = content;
    }
}
