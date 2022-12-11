package cn.wsg.springboot.service;

import cn.wsg.springboot.common.JwtUtil;
import cn.wsg.springboot.mapper.UserMapper;
import cn.wsg.springboot.pojo.LoginUser;
import cn.wsg.springboot.pojo.SysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    public UserService(UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    public String login(SysUser user) {
        // todo query database to check
        SysUser sysUser = userMapper.getUserByUsername(user.getUsername());
        if (sysUser == null) {
            throw new RuntimeException("user not exist");
        }
        // todo add to redis to store logged users
        return JwtUtil.createToken(sysUser, false);
    }

    public void logout() {
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticated.getPrincipal();
        // todo delete user from redis
    }
}
