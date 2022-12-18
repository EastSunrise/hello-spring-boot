package cn.wsg.springboot.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Parameters when logging in.
 *
 * @author Kingen
 */
@Getter
@Setter
@ToString
public class LoginUserDTO {

    private String username;
    private String password;
}
