package cn.wsg.springboot.service;

import cn.wsg.springboot.common.JwtUtil;
import cn.wsg.springboot.mapper.AdminUserRepository;
import cn.wsg.springboot.mapper.UserRepository;
import cn.wsg.springboot.pojo.dto.LoggedUserDTO;
import cn.wsg.springboot.pojo.dto.LoginUserDTO;
import cn.wsg.springboot.pojo.entity.AdminUserEntity;
import cn.wsg.springboot.pojo.entity.UserEntity;
import cn.wsg.springboot.pojo.enums.UserScope;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Kingen
 */
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private AdminUserRepository adminUserRepository;

    public ResponseEntity<String> login(LoginUserDTO loginUserDTO) {
        UserEntity user = userRepository.findByUsername(loginUserDTO.getUsername());
        if (user == null || !Objects.equals(user.getPassword(), loginUserDTO.getPassword())) {
            return ResponseEntity.badRequest().body("用户名或密码错误");
        }
        return ResponseEntity.ok(JwtUtil.createToken(new LoggedUserDTO(user.getId(), UserScope.USER)));
    }

    public ResponseEntity<String> loginAdmin(LoginUserDTO loginUserDTO) {
        AdminUserEntity user = adminUserRepository.findByUsername(loginUserDTO.getUsername());
        if (user == null || !Objects.equals(user.getPassword(), loginUserDTO.getPassword())) {
            return ResponseEntity.badRequest().body("用户名或密码错误");
        }
        return ResponseEntity.ok(JwtUtil.createToken(new LoggedUserDTO(user.getId(), UserScope.ADMIN)));
    }
}
