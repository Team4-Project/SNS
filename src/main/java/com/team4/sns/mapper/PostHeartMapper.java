package com.team4.sns.mapper;

import com.team4.sns.vo.PostHeartVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostHeartMapper {

    void insertHeart(PostHeartVO postHeartVO);

    void updateHeartCancel(PostHeartVO postHeartVO);

    Integer isValidateHeart(PostHeartVO postHeartVO);
}
