package cn.wsg.springboot.pojo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private final int userId;
    private final String username;
    private final String password;

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}
