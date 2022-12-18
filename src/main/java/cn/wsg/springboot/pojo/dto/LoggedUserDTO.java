package cn.wsg.springboot.pojo.dto;

import cn.wsg.springboot.pojo.enums.UserScope;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Kingen
 */
@Getter
@ToString
public class LoggedUserDTO {

    private final int userId;
    private final UserScope scope;

    public LoggedUserDTO(int userId, UserScope scope) {
        this.userId = userId;
        this.scope = scope;
    }
}
