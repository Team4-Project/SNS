package com.team4.sns.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    Integer id;
    String account;
    String password;
    String name;
    String nickname;
    String profession;
    Integer gender;
    String content;
    Date birthday;
    String imageUrl;
    Integer isAuth;
    Date createdAt;
    Date updatedAt;
    Integer isDeleted;
    public User(Integer id, String account, String password, String name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
    }
    public User(String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
    }
    public User(String account, String name) {
        this.account = account;
        this.name = name;
    }
    public User(String nickname, String profession, Integer gender, String content, String image, Integer isAuth) {
        this.nickname = nickname;
        this.profession = profession;
        this.gender = gender;
        this.content = content;
        this.imageUrl = image;
        this.isAuth = isAuth;
    }
}
