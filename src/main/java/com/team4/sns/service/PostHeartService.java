package com.team4.sns.service;

import com.team4.sns.controller.dto.HeartDto;

public interface PostHeartService {

    void insertHeart(HeartDto heartDTO);

    void deleteHeart(HeartDto heartDTO);

    boolean isValidateHeart(HeartDto heartDTO);
}
