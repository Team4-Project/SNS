package com.team4.sns.service;

import com.team4.sns.mapper.UserSessionMapper;
import com.team4.sns.vo.User;
import com.team4.sns.vo.UserSession;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
    private UserSessionMapper userSessionMapper;

    public UserSessionService(UserSessionMapper userSessionMapper) {
        this.userSessionMapper = userSessionMapper;
    }

    public Integer createUserSession(User user) {
        UserSession userSession = new UserSession(user.getId(), user.getName());
        userSessionMapper.createUserSession(userSession);
        return userSession.getId();
    }
    public UserSession getUserSessionById(Integer sessionId) {
        return userSessionMapper.getUserSessionById(sessionId);
    }
    public void deleteUserSession(Integer sessionId) {
        userSessionMapper.deleteUserSession(sessionId);
    }
}
