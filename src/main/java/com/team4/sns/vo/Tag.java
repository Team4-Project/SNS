package com.team4.sns.vo;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tag {
    Integer id;
    Integer postId;
    String content;
    Date createdAt;
    Date updatedAt;
    Integer isDeleted;

    public Tag(Integer postId, String content) {
        this.postId = postId;
        this.content = content;
    }
    public Tag(Integer id, Integer postId) {
        this.id = id;
        this.postId = postId;
    }
    public Tag(Integer id, Integer postId, String content) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }
}
