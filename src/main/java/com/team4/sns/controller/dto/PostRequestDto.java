package com.team4.sns.controller.dto;

import com.team4.sns.vo.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostRequestDto {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @NotBlank(message = "내용을 입력해주세요")
    @Size(min=1, max=500, message = "글은 1~500자 사이로 입력해주세요.")
    private String content;

    public Post toPost(){
        return Post.builder()
                .userId(userId)
                .content(content)
                .build();
    }
}
