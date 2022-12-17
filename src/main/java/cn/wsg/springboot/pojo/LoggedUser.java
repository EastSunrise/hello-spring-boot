package cn.wsg.springboot.pojo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoggedUser {

    private final int userId;
    private final UserScope scope;

    public LoggedUser(int userId, UserScope scope) {
        this.userId = userId;
        this.scope = scope;
    }
}
