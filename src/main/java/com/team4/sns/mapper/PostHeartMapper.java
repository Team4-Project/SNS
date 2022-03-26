package com.team4.sns.mapper;

import com.team4.sns.controller.dto.HeartDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostHeartMapper {

    void insertHeart(HeartDto heartDTO);

    void deleteHeart(HeartDto heartDTO);

    Integer isValidateHeart(HeartDto heartDTO);
}
