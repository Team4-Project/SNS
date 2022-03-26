package com.team4.sns.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyLikeMapper {
    Integer likeReply(@Param("id") Integer id, @Param("userId") Integer userId);
    Integer unlikeReply(@Param("id") Integer id, @Param("userId") Integer userId);
}
