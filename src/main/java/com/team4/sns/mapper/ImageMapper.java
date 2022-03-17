package com.team4.sns.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageMapper {
    void insertImageUrl(@Param("postId") Long postId, @Param("imageUrls") List<String> imageUrls);
}
