package cn.wsg.springboot.mapper;

import cn.wsg.springboot.pojo.SysUser;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private static final SysUser ADMIN = SysUser.builder().userId(0).username("admin").password("$2a$10$dKSdgAa9BgvHIX24KnR65eFZDRzDVZqyEA8DN7.PKd9AGpHKIdNKa").phoneNumber("123456").build();
    private static final SysUser TEST = SysUser.builder().userId(1).username("test").password("$2a$10$KKa2np7iiBh.fa1QImV5kunSJzKK8faNA/kNsMjCHMIra.o2nmheK").phoneNumber("456789").build();

    @Nullable
    public SysUser getUserByUsername(String username) {
        if (TEST.getUsername().equals(username)) {
            return TEST;
        }
        return null;
    }

    @Nullable
    public SysUser getAdminUserByUsername(String username) {
        if (ADMIN.getUsername().equals(username)) {
            return ADMIN;
        }
        return null;
    }
}
