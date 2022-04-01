package com.team4.sns.serviceImpl;

import com.team4.sns.vo.PostHeartVO;
import com.team4.sns.mapper.PostHeartMapper;
import com.team4.sns.service.PostHeartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostHeartServiceImpl implements PostHeartService {

    private PostHeartMapper postHeartMapper;
    @Override
    public void insertHeart(PostHeartVO postHeartVO) {
        postHeartMapper.insertHeart(postHeartVO);
    }

    @Override
    public void deleteHeart(PostHeartVO postHeartVO) {
        postHeartMapper.updateHeartCancel(postHeartVO);
    }

}
