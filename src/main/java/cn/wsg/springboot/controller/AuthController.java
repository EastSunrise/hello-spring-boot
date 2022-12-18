package cn.wsg.springboot.controller;

import cn.wsg.springboot.pojo.dto.LoginUserDTO;
import cn.wsg.springboot.service.UserService;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kingen
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/login/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> login(LoginUserDTO user) {
        return userService.login(user);
    }

    @PostMapping(value = "/login/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> loginAdmin(LoginUserDTO user) {
        return userService.loginAdmin(user);
    }
}
