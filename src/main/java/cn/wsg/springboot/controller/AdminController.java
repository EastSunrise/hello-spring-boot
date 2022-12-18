package cn.wsg.springboot.controller;

import cn.wsg.springboot.common.PageResult;
import cn.wsg.springboot.common.Pagination;
import cn.wsg.springboot.pojo.dto.QueryRecordDTO;
import cn.wsg.springboot.pojo.dto.SaveRecordDTO;
import cn.wsg.springboot.pojo.entity.UserRecordEntity;
import cn.wsg.springboot.service.UserRecordService;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kingen
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserRecordService recordService;

    @PostMapping(value = "/record/add")
    public ResponseEntity<?> saveRecord(@Valid SaveRecordDTO saveRecordDTO) {
        return recordService.saveRecord(saveRecordDTO);
    }

    @GetMapping("/record/query")
    public HttpEntity<PageResult<UserRecordEntity>> listRecords(@Valid QueryRecordDTO query, Pagination pagination) {
        return recordService.listRecords(query, pagination);
    }
}
