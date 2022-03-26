package com.team4.sns.service;

import com.team4.sns.mapper.ReplyLikeMapper;
import com.team4.sns.vo.UserSession;
import org.springframework.stereotype.Service;

@Service
public class ReplyLikeService {
    private ReplyLikeMapper replyLikeMapper;
    private UserSessionService userSessionService;
    public ReplyLikeService(ReplyLikeMapper replylikeMapper, UserSessionService userSessionService) {
        this.replyLikeMapper = replylikeMapper;
        this.userSessionService = userSessionService;
    }

    public Integer likeReply(Integer id, Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }
        // GET logInUserId
        Integer logInUserId = userSession.getUserId();

        // FAIL create reply
        if (replyLikeMapper.likeReply(id, logInUserId) != 1) {
            return -3;
        }
        return 1;
    }

    public Integer unlikeReply(Integer id, Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }
        // GET logInUserId
        Integer logInUserId = userSession.getUserId();

        // FAIL create reply
        if (replyLikeMapper.unlikeReply(id, logInUserId) != 1) {
            return -3;
        }
        return 1;
    }
}
