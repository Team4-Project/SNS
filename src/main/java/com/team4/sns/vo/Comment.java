package com.team4.sns.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class Comment {

    private Long id;
    private Long postId;
    private Long userId;
    private String content;
    private Timestamp wroteAt;
    private Timestamp updatedAt;

    private User user;

    @Builder
    public Comment(Long postId, Long userId, String content){
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }
}
