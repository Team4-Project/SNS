package com.team4.sns.serviceImpl;

import com.team4.sns.mapper.TestMapper;
import com.team4.sns.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Override
    public String test() {
        return testMapper.test();
    }
}
