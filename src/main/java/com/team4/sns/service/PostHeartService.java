package com.team4.sns.service;

import com.team4.sns.vo.PostHeartVO;

public interface PostHeartService {

    void insertHeart(PostHeartVO postHeartVO);

    void deleteHeart(PostHeartVO postHeartVO);

}
