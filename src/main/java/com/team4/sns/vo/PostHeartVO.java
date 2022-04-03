package com.team4.sns.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostHeartVO {

    int userId;
    int postId;
    int isDeleted;
}
