package com.team4.sns.mapper;

import com.team4.sns.vo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {

    void writePost(@Param("post") Post post);
}
