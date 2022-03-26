package com.team4.sns.mapper;

import com.team4.sns.vo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyMapper {
    Integer createReply(@Param("reply") Reply reply);
    Integer editReply(@Param("reply") Reply reply);
    Integer deleteReply(@Param("id") Integer id);
    Reply getReplyById(@Param("id") Integer id);
}
