package com.team4.sns.serviceImpl;

import com.team4.sns.controller.dto.HeartDto;
import com.team4.sns.mapper.PostHeartMapper;
import com.team4.sns.service.PostHeartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostHeartServiceImpl implements PostHeartService {

    private PostHeartMapper postHeartMapper;
    @Override
    public void insertHeart(HeartDto heartDTO) {
        postHeartMapper.insertHeart(heartDTO);
    }

    @Override
    public void deleteHeart(HeartDto heartDTO) {
        postHeartMapper.deleteHeart(heartDTO);
    }

    @Override
    public boolean isValidateHeart(HeartDto heartDTO) {
        if (postHeartMapper.isValidateHeart(heartDTO) != null) {
            return true;
        }
        return false;
    }
}
