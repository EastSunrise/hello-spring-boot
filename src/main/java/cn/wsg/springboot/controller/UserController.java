package cn.wsg.springboot.controller;

import cn.wsg.springboot.pojo.SysUser;
import cn.wsg.springboot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(SysUser user) {
        String token = userService.login(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        userService.logout();
        return ResponseEntity.ok("Log out");
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/test/hello")
    public ResponseEntity<String> helloTest() {
        return ResponseEntity.ok("Hello World");
    }
}
