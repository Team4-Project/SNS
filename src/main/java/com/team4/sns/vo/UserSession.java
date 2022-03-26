package com.team4.sns.vo;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSession {
    Integer id;
    Integer userId;
    String name;
    Date createdAt;
    Date finishedAt;

    public UserSession(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

}
