package com.team4.sns.serviceImpl;

import com.team4.sns.DTO.HeartDTO;
import com.team4.sns.mapper.PostHeartMapper;
import com.team4.sns.service.PostHeartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostHeartServiceImpl implements PostHeartService {

    private PostHeartMapper postHeartMapper;
    @Override
    public void insertHeart(HeartDTO heartDTO) {
        postHeartMapper.insertHeart(heartDTO);
    }

    @Override
    public void deleteHeart(HeartDTO heartDTO) {
        postHeartMapper.deleteHeart(heartDTO);
    }

    @Override
    public boolean isValidateHeart(HeartDTO heartDTO) {
        if (postHeartMapper.isValidateHeart(heartDTO) != null) {
            return true;
        }
        return false;
    }
}
