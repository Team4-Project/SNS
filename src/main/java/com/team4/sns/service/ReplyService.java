package com.team4.sns.service;

import com.team4.sns.mapper.ReplyMapper;
import com.team4.sns.vo.Reply;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReplyService {
    private ReplyMapper replyMapper;
    public ReplyService(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public boolean createReply(Reply reply) {
        Integer createReplyResult = replyMapper.createReply(reply);
        return createReplyResult == 1;
    }

    public boolean editReply(Reply reply, Integer logInUserId) {
        // DB에 저장된 reply userId 와 logInUserId 가 같으면 edit 실행
        Reply originalReply = replyMapper.getReplyById(reply.getId());
        if (originalReply.getUserId() != logInUserId) {
            return false;
        }
        Integer editReplyResult = replyMapper.editReply(reply);
        return editReplyResult == 1;
    }

    public boolean deleteReply(Integer id, Integer logInUserId) {
        // DB에 저장된 reply userId 와 logInUserId 가 같으면 delete 실행
        Reply originalReply = replyMapper.getReplyById(id);
        if (originalReply.getUserId() != logInUserId) {
            return false;
        }
        Integer deleteReplyResult = replyMapper.deleteReply(id);
        return deleteReplyResult == 1;
    }

}
