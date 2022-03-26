package com.team4.sns.service;

import com.team4.sns.DTO.HeartDTO;

public interface PostHeartService {

    void insertHeart(HeartDTO heartDTO);

    void deleteHeart(HeartDTO heartDTO);

    boolean isValidateHeart(HeartDTO heartDTO);
}
