package cn.wsg.springboot.controller;

import cn.wsg.springboot.pojo.LoginUser;
import cn.wsg.springboot.service.UserService;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(LoginUser user) {
        return userService.login(user);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<String> loginAdmin(LoginUser user) {
        return userService.loginAdmin(user);
    }
}
