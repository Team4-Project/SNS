package com.team4.sns.service;

import com.team4.sns.mapper.ReplyMapper;
import com.team4.sns.vo.Reply;
import com.team4.sns.vo.UserSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReplyService {
    private ReplyMapper replyMapper;
    private UserSessionService userSessionService;
    public ReplyService(ReplyMapper replyMapper, UserSessionService userSessionService) {
        this.replyMapper = replyMapper;
        this.userSessionService = userSessionService;
    }

    public Integer createReply(Reply reply, Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }
        // GET logInUserId
        Integer logInUserId = userSession.getUserId();

        // SET userID -> reply 객체
        reply.setUserId(logInUserId);

        // FAIL create reply
        if (replyMapper.createReply(reply) != 1) {
            return -3;
        }
        return 1;
    }

    public Integer editReply(Reply reply, Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }
        // CHECK "현재 로그인한 유저"와 "수정하려는 대댓글의 userId"가 다르면 error
        Integer logInUserId = userSession.getUserId();
        Reply originalReply = replyMapper.getReplyById(reply.getId());
        if (originalReply.getUserId() != logInUserId) {
            return -2;
        }
        // FAIL edit reply
        if (replyMapper.editReply(reply) != 1) {
            return -3;
        }
        return 1;
    }

    public Integer deleteReply(Integer id, Integer sessionId) {
        // VALIDATE 로그인 유저 세션
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if (userSession == null) {
            return -1;
        }
        // CHECK "현재 로그인한 유저"와 "지우려는 대댓글의 userId"가 다르면 error
        Integer logInUserId = userSession.getUserId();
        Reply originalReply = replyMapper.getReplyById(id);
        if (originalReply.getUserId() != logInUserId) {
            return -2;
        }
        // FAIL delete reply
        if (replyMapper.deleteReply(id) != 1) {
            return -3;
        }
        return 1;
    }

}
