package com.team4.sns.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartDTO {

    int userId;
    int postId;
    int isDeleted;
}
