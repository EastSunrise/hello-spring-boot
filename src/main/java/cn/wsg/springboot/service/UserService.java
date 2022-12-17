package cn.wsg.springboot.service;

import cn.wsg.springboot.common.JwtUtil;
import cn.wsg.springboot.mapper.UserMapper;
import cn.wsg.springboot.pojo.LoggedUser;
import cn.wsg.springboot.pojo.LoginUser;
import cn.wsg.springboot.pojo.User;
import cn.wsg.springboot.pojo.UserScope;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public ResponseEntity<String> login(LoginUser loginUser) {
        User user = userMapper.getUserByUsername(loginUser);
        if (user == null) {
            return ResponseEntity.badRequest().body("用户名或密码错误");
        }
        return ResponseEntity.ok(JwtUtil.createToken(new LoggedUser(user.getUserId(), UserScope.USER)));
    }

    public ResponseEntity<String> loginAdmin(LoginUser loginUser) {
        User user = userMapper.getAdminUserByUsername(loginUser);
        if (user == null) {
            return ResponseEntity.badRequest().body("用户名或密码错误");
        }
        return ResponseEntity.ok(JwtUtil.createToken(new LoggedUser(user.getUserId(), UserScope.ADMIN)));
    }
}
