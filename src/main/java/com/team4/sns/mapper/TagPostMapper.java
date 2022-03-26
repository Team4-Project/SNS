package com.team4.sns.mapper;


import com.team4.sns.vo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagPostMapper {
    Integer createTagPost(@Param("postId") Integer postId, @Param("tagId") Integer tagId);
    Integer deleteTagPost(@Param("postId") Integer postId, @Param("tagId") Integer tagId);
}
