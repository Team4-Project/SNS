package com.team4.sns.mapper;

import com.team4.sns.vo.UserSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserSessionMapper {
    Integer createUserSession(@Param("userSession") UserSession userSession);
    UserSession getUserSessionById(@Param("sessionId") Integer sessionId);
    Integer deleteUserSession(@Param("sessionId") Integer sessionId);
}
