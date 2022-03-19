package com.team4.sns.service;


import com.team4.sns.mapper.UserMapper;
import com.team4.sns.vo.User;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private UserMapper userMapper;
    private UserSessionService userSessionService;

    public UserService(UserMapper userMapper, UserSessionService userSessionService) {
        this.userMapper = userMapper;
        this.userSessionService = userSessionService;
    }

    public boolean getUserByAccount(User user) {
        User userResult = userMapper.getUserByAccount(user.getAccount());
        if (userResult == null) {
            return true;
        }
        else {
            return false;
        }
    }
    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public Integer loginUser(User user) {
        // 요청 들어온 user 정보가 db에 있는지 확인
        User userResult = userMapper.getUserByAccountAndPassword(user.getAccount(), user.getPassword());
        System.out.println(userResult);
        // db에 존재하면 로그인 진행하고 user_session 생성
        if (userResult == null) {
            return -1;
        }
        Integer sessionId = userSessionService.createUserSession(userResult);
        return sessionId;
    }

    public void updateUser(User user, Integer userId) {
        // user:   수정될 user 정보
        // userId: 현재 로그인한 사람의 userId
        userMapper.updateUser(user, userId);
    }

    public void deleteUser(Integer userId) {
        userMapper.deleteUser(userId);
    }


}
