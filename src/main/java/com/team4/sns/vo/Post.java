package com.team4.sns.vo;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Post {

    private Long id;
    private Long userId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder
    public Post(Long userId, String content){
        this.userId = userId;
        this.content = content;
    }
}
