package com.team4.sns.mapper;

import com.team4.sns.vo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> getTagListByPostId(@Param("postId") Integer postId);
    Integer createTag(@Param("tag") Tag tag);
    Tag getTag(@Param("tagId") Integer tagId);
    Integer editTag(@Param("tag") Tag tag);
    Integer deleteTag(@Param("tag") Tag tag);
    List<Tag> getTagListSearch(@Param("content") String content, @Param("limit") Integer limit, @Param("offset") Integer offset);
}
