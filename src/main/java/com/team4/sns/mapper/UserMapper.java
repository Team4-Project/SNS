package com.team4.sns.mapper;

import com.team4.sns.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Integer createUser(@Param("user") User user);
    User getUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);
    User getUserByAccount(@Param("account") String account);
    User getUserById(@Param("userId") Integer userId);
    Integer editUser(@Param("user") User user);
    Integer deleteUser(@Param("id") Integer id);
    Integer getUser(User user);
}
