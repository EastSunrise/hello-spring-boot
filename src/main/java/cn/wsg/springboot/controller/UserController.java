package cn.wsg.springboot.controller;

import cn.wsg.springboot.pojo.entity.UserRecordEntity;
import cn.wsg.springboot.service.UserRecordService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kingen
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRecordService recordService;

    @GetMapping("/record/query")
    public ResponseEntity<List<UserRecordEntity>> listRecords() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return recordService.listRecordsByUser((Integer) authentication.getPrincipal());
    }
}
