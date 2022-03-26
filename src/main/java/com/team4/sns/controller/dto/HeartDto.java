package com.team4.sns.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartDto {

    int userId;
    int postId;
    int isDeleted;
}
