package cn.wsg.springboot.mapper;

import cn.wsg.springboot.pojo.LoginUser;
import cn.wsg.springboot.pojo.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private static final User ADMIN = new User(0, "admin", "admin");
    private static final User TEST = new User(1, "test", "test");

    @Nullable
    public User getUserByUsername(LoginUser user) {
        if (TEST.getUsername().equals(user.getUsername()) && TEST.getPassword().equals(user.getPassword())) {
            return TEST;
        }
        return null;
    }

    @Nullable
    public User getAdminUserByUsername(LoginUser user) {
        if (ADMIN.getUsername().equals(user.getUsername()) && ADMIN.getPassword().equals(user.getPassword())) {
            return ADMIN;
        }
        return null;
    }
}
