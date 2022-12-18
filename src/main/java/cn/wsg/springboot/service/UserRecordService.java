package cn.wsg.springboot.service;

import cn.wsg.springboot.common.PageResult;
import cn.wsg.springboot.common.Pagination;
import cn.wsg.springboot.mapper.UserRecordRepository;
import cn.wsg.springboot.mapper.UserRepository;
import cn.wsg.springboot.pojo.dto.QueryRecordDTO;
import cn.wsg.springboot.pojo.dto.SaveRecordDTO;
import cn.wsg.springboot.pojo.entity.UserEntity;
import cn.wsg.springboot.pojo.entity.UserRecordEntity;
import cn.wsg.springboot.pojo.entity.UserRecordEntity_;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Kingen
 */
@Slf4j
@Service
public class UserRecordService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserRecordRepository recordRepository;

    public ResponseEntity<List<UserRecordEntity>> listRecordsByUser(int userId) {
        List<UserRecordEntity> records = recordRepository.findAllByUserId(userId);
        return ResponseEntity.ok(records);
    }

    public ResponseEntity<?> saveRecord(SaveRecordDTO saveRecordDTO) {
        Optional<UserEntity> userOp = userRepository.findById(saveRecordDTO.getUserId());
        if (userOp.isEmpty()) {
            return ResponseEntity.badRequest().body("用户不存在");
        }
        UserRecordEntity recordEntity = new UserRecordEntity();
        BeanUtils.copyProperties(saveRecordDTO, recordEntity);
        recordEntity = recordRepository.save(recordEntity);
        log.info("a record is saved, id={}", recordEntity.getId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<PageResult<UserRecordEntity>> listRecords(QueryRecordDTO cond, Pagination pagination) {
        Specification<UserRecordEntity> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (cond.getUserId() != null) {
                predicates.add(builder.equal(root.get(UserRecordEntity_.userId), cond.getUserId()));
            }
            if (StringUtils.isNotBlank(cond.getRecordName())) {
                predicates.add(builder.like(root.get(UserRecordEntity_.recordName), "%" + cond.getRecordName() + "%"));
            }
            if (cond.getRecordType() != null) {
                predicates.add(builder.equal(root.get(UserRecordEntity_.recordType), cond.getRecordType()));
            }
            if (cond.getMonth() != null) {
                LocalDateTime startTime = cond.getMonth().atDay(1).atStartOfDay();
                predicates.add(builder.greaterThanOrEqualTo(root.get(UserRecordEntity_.recordTime), startTime));
                LocalDateTime endTime = cond.getMonth().plusMonths(1).atDay(1).atStartOfDay();
                predicates.add(builder.lessThan(root.get(UserRecordEntity_.recordTime), endTime));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
        return ResponseEntity.ok(new PageResult<>(recordRepository.findAll(spec, pagination)));
    }
}
