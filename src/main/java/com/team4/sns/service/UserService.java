package com.team4.sns.service;


import com.team4.sns.mapper.UserMapper;
import com.team4.sns.util.S3Util;
import com.team4.sns.vo.User;
import com.team4.sns.vo.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private UserMapper userMapper;
    private UserSessionService userSessionService;
    private final S3Util s3Util;

    public UserService(UserMapper userMapper, UserSessionService userSessionService, S3Util s3Util) {
        this.userMapper = userMapper;
        this.userSessionService = userSessionService;
        this.s3Util = s3Util;
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

    public Integer editUser(User user, List<MultipartFile> images, Integer sessionId) throws IOException {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }

        // sessionId를 통해 현재 로그인한 사람의 id를 구함
        Integer logInUserId = userSession.getUserId();
        user.setId(logInUserId);

        // 프로필 이미지 upload 되었으면 함께 반영
        if(images != null) {
            List<String> uploadedImageUrls = s3Util.uploadObject(images);
            String imageUrl = uploadedImageUrls.get(0);
            user.setImageUrl(imageUrl);
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
    public User getUserBySessionId(Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return null;
        }

        // sessionId를 통해 현재 로그인한 사람의 id를 구함
        Integer logInUserId = userSession.getUserId();
        return userMapper.getUserById(logInUserId);
    }

    public List<User> getSevenRecommendationAboutUser(Integer userId){
        return userMapper.getSevenRecommendationAboutUser(userId);
    }

    public List<User> getUserByKeyword(String keyword){
        return userMapper.getUserByKeyword(keyword);
    }

    public List<User> getNinePopularUser(Integer userId){
        return userMapper.getNinePopularUser(userId);
    }

    public User getUserInfo(Integer userId){
        return userMapper.getUserById(userId);
      
    public List<User> getTrendingUserList() {
        return userMapper.getTrendingUserList();
    }
}
