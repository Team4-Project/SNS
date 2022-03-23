package com.team4.sns.vo;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {
    Integer id;
    Integer commentId;
    Integer userId;
    String content;
    Date createdAt;
    Date updatedAt;
    Integer isDeleted;

    public Reply (Integer commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public Reply (Integer id, Integer commentId, String content) {
        this.id = id;
        this.commentId = commentId;
        this.content = content;
    }

}
