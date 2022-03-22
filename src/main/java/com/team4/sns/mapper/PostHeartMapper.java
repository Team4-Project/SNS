package com.team4.sns.mapper;

import com.team4.sns.DTO.HeartDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostHeartMapper {

    void insertHeart(HeartDTO heartDTO);

    void deleteHeart(HeartDTO heartDTO);

    Integer isValidateHeart(HeartDTO heartDTO);
}
