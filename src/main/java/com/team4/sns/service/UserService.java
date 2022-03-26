package com.team4.sns.service;


import com.team4.sns.mapper.UserMapper;
import com.team4.sns.vo.User;
import com.team4.sns.vo.UserSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 신규 user가 생성하려는 이메일(account) 중복 체크
    public boolean getUserByAccount(User user) {
        User userResult = userMapper.getUserByAccount(user.getAccount());
        if (userResult == null) {
            return true;
        }
        else {
            return false;
        }
    }
    public Integer createUser(User user) {
        // VALIDATE 이메일(account) 중복
        boolean userResult = getUserByAccount(user);
        if (userResult == false) {
            return -1;
        }
        if (userMapper.createUser(user) != 1) {
            return -3;
        }
        return 1;

    }

    public Integer loginUser(User user) {
        // CHECK 로그인 하려는 user 정보
        User userResult = userMapper.getUserByAccountAndPassword(user.getAccount(), user.getPassword());
        // DB에 존재하면 로그인 진행하고 user_session 생성
        if (userResult == null) {
            return -1;
        }
        Integer sessionId = userSessionService.createUserSession(userResult);
        return sessionId;
    }

    public Integer editUser(User user, Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }
        // CHECK "현재 로그인한 유저"와 "수정하려는 유저의 userId"가 다르면 error
        Integer logInUserId = userSession.getUserId();
        if (user.getId().equals(logInUserId) == false) {
            return -2;
        }
        if (userMapper.editUser(user) != 1) {
            return -3;
        }
        return 1;
    }

    public Integer deleteUser(Integer id, Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        // 존재하지 않는 session 이라면 error
        // 즉, 세션 시간 만료로 세션 삭제 등등
        if (userSession == null) {
            return -1;
        }
        // "현재 로그인한 유저"와 "지우려는 유저정보"가 다른 userId를 가지고 있다면 error
        Integer logInUserId = userSession.getUserId();
        if (id.equals(logInUserId) == false) {
            return -2;
        }
        if (userMapper.deleteUser(id) != 1) {
            return -3;
        }
        return 1;
    }
    public void getUser(User user) {
        Integer id = userMapper.getUser(user);
    }

}
